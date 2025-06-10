import java.util.*;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class customVisitor extends GymlangBaseVisitor<String> {
    private String libraryName = "";
    private Set<String> muscleGroup = new HashSet<>();
    private Map<String, Set<String>> gymExercise = new HashMap<>();
    private int timePerSession = -1;
    private int daysPerWeek = -1;
    private Map<String, List<ExerciseData>> weeklyPlan = new LinkedHashMap<>();
    private Map<String, DefinedSet> definedSets = new HashMap<>();

    // Exercise Muscles Volume
    private Map<String, Integer> muscleVolumeGoal = new HashMap<>();
    private Map<String, Integer> workoutVolume = new HashMap<>();
    private List<String> warnings = new ArrayList<>();

    // current list of sets
    private List<SetData> currentSets = new ArrayList<>();
    // current list of exercises
    private Map<String, List<ExerciseData>> currentExerciseList = new HashMap<>();
    // current set type
    private String currentSetType= "";
    private String currentDay = "";

    @Override
    public String visitStmt(GymlangParser.StmtContext ctx) {
        visit(ctx.muscleGroup());
        visit(ctx.gym());
        visit(ctx.defSet());
        visit(ctx.block());
        checkWarnings();
        printWorkoutVolume();
        return generateWorkoutPlan();
    }

    @Override
    public String visitMuscleGroup(GymlangParser.MuscleGroupContext ctx) {
        visit(ctx.muscleList());
        return "";
    }

    @Override
    public String visitMuscleList(GymlangParser.MuscleListContext ctx) {
        for (TerminalNode nameNode : ctx.NAME()) {
            String muscle = nameNode.getText();
            if (muscleGroup.contains(muscle)) {
                throw new RuntimeException("Error: Duplicate muscle group detected - '" + muscle + "'");
            }
            muscleGroup.add(muscle);
        }
        return "";
    }

    @Override
    public String visitGym(GymlangParser.GymContext ctx) {
        libraryName = ctx.NAME().getText();
        for (GymlangParser.ExerciseDefinitionContext exercise : ctx.exerciseDefinition()) {
            String exerciseName = exercise.NAME().getText();
            Set<String> targetMuscles = new HashSet<>();

            for (TerminalNode muscleNode : exercise.muscleList().NAME()) {
                String muscle = muscleNode.getText();
                if (!muscleGroup.contains(muscle)) {
                    throw new RuntimeException("Error: Undefined muscle group '" + muscle + "' in exercise '" + exerciseName + "'");
                }
                targetMuscles.add(muscle);
            }

            gymExercise.put(exerciseName, targetMuscles);
        }
        return "";
    }

    @Override
    public String visitDefSet(GymlangParser.DefSetContext ctx) {
        List<TerminalNode> names = ctx.NAME();
        List<GymlangParser.SetDefContext> setDefs = ctx.setDef();


        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).getText();
//            System.out.println(name);

            List<SetExpression> expressions = new ArrayList<>();
            GymlangParser.SetDefContext setBlock = setDefs.get(i);
//            System.out.println("set block: " + setBlock.getText());
            for (GymlangParser.SetLineContext setLine : setBlock.getRuleContexts(GymlangParser.SetLineContext.class)) {
                GymlangParser.SetDefExprContext weightExpr = setLine.setDefExpr(0);
                GymlangParser.SetDefExprContext repsExpr = setLine.setDefExpr(1);
                float weight = 0;
                int reps = 0;


                // weightExpr: WEIGHT (+/- NUMBER)?
                if (weightExpr.WEIGHT() != null && weightExpr.editSetDef() != null) {
                    String sign = weightExpr.editSetDef().getChild(0).getText(); // "+" or "-"
                    int offset = Integer.parseInt(weightExpr.editSetDef().NUMBER().getText());
                    weight = sign.equals("-") ? -offset : offset;
                }

                // repsExpr: REPS (+/- NUMBER)?
                if (repsExpr.REPS() != null && repsExpr.editSetDef() != null) {
                    String sign = repsExpr.editSetDef().getChild(0).getText();
                    int offset = Integer.parseInt(repsExpr.editSetDef().NUMBER().getText());
                    reps = sign.equals("-") ? -offset : offset;
                }
                expressions.add(new SetExpression(weight, reps));
//                System.out.println("Parsed Set(weight=" + weight + ", reps=" + reps + ")");
            }

            definedSets.put(name, new DefinedSet(name, expressions));
        }
        return null;
    }

    @Override
    public String visitBlock(GymlangParser.BlockContext ctx) {
        visit(ctx.progGoal());
        visit(ctx.dayPlan());
        visit(ctx.weekPlan());
        return "";
    }

    // Program Goals
    @Override
    public String visitProgGoal(GymlangParser.ProgGoalContext ctx) {
        visit(ctx.sessionLen());
        visit(ctx.daysPerWeek());
        if (timePerSession == -1) {
            throw new RuntimeException("Error: 'timePerSession' not specified.");
        }
        if (daysPerWeek == -1) {
            throw new RuntimeException("Error: 'daysPerWeek' not specified.");
        }
        visit(ctx.volumeGoal());
        return "";
    }

    @Override
    public String visitSessionLen(GymlangParser.SessionLenContext ctx) {
        if (ctx.TPS() == null || !ctx.TPS().getText().equals("timePerSession")) {
            String found = (ctx.TPS() != null) ? ctx.TPS().getText() : "null";
            throw new RuntimeException("Syntax Error: Expected 'timePerSession = number' but found '" + found + "'");
        }
        timePerSession = Integer.parseInt(ctx.NUMBER().getText());
        if (timePerSession > 240) {
            throw new RuntimeException("Error: Time per session is too long (" + timePerSession + " minutes).");
        }
        return "";
    }

    @Override
    public String visitDaysPerWeek(GymlangParser.DaysPerWeekContext ctx) {
        if (ctx.DPW() == null || !ctx.DPW().getText().equals("daysPerWeek")) {
            String found = (ctx.DPW() != null) ? ctx.DPW().getText() : "null";
            throw new RuntimeException("Syntax Error: Expected 'daysPerWeek = number' but found '" + found + "'");
        }
        daysPerWeek = Integer.parseInt(ctx.NUMBER().getText());
        return "";
    }

    @Override
    public String visitVolumeGoal(GymlangParser.VolumeGoalContext ctx) {
        for (GymlangParser.MuscleVolumeContext muscle : ctx.muscleVolume()) {
            String muscleName = muscle.NAME().getText();
            if(!muscleGroup.contains(muscleName)) {
                throw new RuntimeException("Error: Undefined muscle group '" + muscleName + "'");
            }
            int volume = Integer.parseInt(muscle.NUMBER().getText());
            muscleVolumeGoal.put(muscleName, volume);
        }
        return "";
    }

    // Daily Plan
    @Override
    public String visitDayPlan(GymlangParser.DayPlanContext ctx) {
        for (int i = 0; i < ctx.NAME().size(); i++) {
            currentDay = ctx.NAME(i).getText();
            visit(ctx.dayBody(i));
        }
        return "";
    }

    @Override
    public String visitDayBody(GymlangParser.DayBodyContext ctx) {
        for (GymlangParser.ExerciseContext exercise : ctx.getRuleContexts(GymlangParser.ExerciseContext.class)) {
            visit(exercise);
        }
        return "";
    }

    @Override
    public String visitExercise(GymlangParser.ExerciseContext ctx) {
        String tempLibName;
        String exerciseName;

        // Get Workout Name
        if (ctx.NAME().size() == 2){
            tempLibName = ctx.NAME(0).getText();
            if(!libraryName.equals(tempLibName)){
                throw new RuntimeException("Error: library '" + tempLibName + "' unnkown.");
            }
            exerciseName = ctx.NAME(1).getText();
        } else if (ctx.NAME().size() == 1) {
            throw new RuntimeException("Syntax Error: Exercise must be in the form 'Library.ExerciseName'.");
        } else {
            exerciseName = ctx.NAME(0).getText();
        }
        // Check if using library && exercise exists in the library
        if(ctx.NAME().size() == 2){
            if(!gymExercise.containsKey(exerciseName)){
                throw new RuntimeException("Error: exercise '" + exerciseName + "' not defined in the library `" + libraryName + "`.");
            }
        }

        Set<String> targetMuscles = gymExercise.get(exerciseName);

        // Get Rest Time
        GymlangParser.RestTimeContext restCtx = ctx.getRuleContext(GymlangParser.RestTimeContext.class, 0);
        if (restCtx == null) {
            throw new RuntimeException("Error: rest time missing for exercise " + exerciseName);
        }
        int restTime = Integer.parseInt(restCtx.NUMBER().getText());

        // Get Sets
        GymlangParser.SetsContext setsCtx = ctx.getRuleContext(GymlangParser.SetsContext.class, 0);
        if (setsCtx == null) {
            throw new RuntimeException("Error: missing sets for exercise " + exerciseName);
        }
        visitSets(setsCtx);
        List<SetData> sets = new ArrayList<>(currentSets);
        currentSets = new ArrayList<>();

        // One Exercise
        currentExerciseList.computeIfAbsent(currentDay, k -> new ArrayList<>()).add(new ExerciseData(exerciseName, restTime, sets, targetMuscles));
//        ExerciseData exerciseData = new ExerciseData(exerciseName, restTime, sets);
        return "";
    }

    @Override
    public String visitSets(GymlangParser.SetsContext ctx) {
        List<SetData> sets = new ArrayList<>();

        // normal sets (weight, reps)
        for (GymlangParser.SetContext setCtx : ctx.getRuleContexts(GymlangParser.SetContext.class)) {
            int weight = Integer.parseInt(setCtx.NUMBER(0).getText());
            int reps = Integer.parseInt(setCtx.NUMBER(1).getText());
            sets.add(new SetData(weight, reps, "default"));
        }

        // Predefined sets
        for (GymlangParser.PredefSetContext predefSetCtx : ctx.getRuleContexts(GymlangParser.PredefSetContext.class)) {
            visit(predefSetCtx);
        }

        currentSets.addAll(sets);
        return "";
    }

    @Override
    public String visitPredefSet(GymlangParser.PredefSetContext ctx) {
        String setType = ctx.NAME().getText();
        float weight = Float.parseFloat(ctx.NUMBER(0).getText());
        int reps = Integer.parseInt(ctx.NUMBER(1).getText());
        currentSetType = setType;

        DefinedSet defined = definedSets.get(currentSetType);
//        System.out.println("Defined set " + currentSetType + ": " + defined.expressions);
        if (defined == null) {
            throw new RuntimeException("Undefined set type: " + currentSetType);
        }

        for (SetExpression expr : defined.expressions) {
            if(weight + expr.weight < 0 || reps + expr.reps < 0){
                throw new RuntimeException("Error: weight or rap goes below zero at " + currentSetType);
            }
            currentSets.add(new SetData(weight + expr.weight, reps+expr.reps, setType));
        }

        if (ctx.editSet() != null) {
            visit(ctx.editSet());
        }

        return "";
    }

    @Override
    public String visitEditSet(GymlangParser.EditSetContext ctx) {
        for (GymlangParser.EditActionContext actionCtx : ctx.getRuleContexts(GymlangParser.EditActionContext.class)) {
            visit(actionCtx);
        }
        return "";
    }

    @Override
    public String visitEditAction(GymlangParser.EditActionContext ctx) {
        if (ctx.removeSet() != null) {
            visit(ctx.removeSet());
        } else if (ctx.addSet() != null) {
            visit(ctx.addSet());
        } else if (ctx.changeSet() != null) {
            visit(ctx.changeSet());
        }
        return "";
    }

    @Override
    public String visitRemoveSet(GymlangParser.RemoveSetContext ctx) {
        int index = Integer.parseInt(ctx.NUMBER().getText()) - 1;

        if (index < 0 || index >= currentSets.size()) {
            throw new RuntimeException("Error: Cannot remove set at index " + index + " (out of bounds)");
        }

        currentSets.remove(index);
//        System.out.println("Removed set at index " + index);
        return "";
    }

    @Override
    public String visitAddSet(GymlangParser.AddSetContext ctx) {
        int weight = Integer.parseInt(ctx.NUMBER(0).getText());
        int reps = Integer.parseInt(ctx.NUMBER(1).getText());

        currentSets.add(new SetData(weight, reps, currentSetType));
//        System.out.println("Added Set(" + weight + ", " + reps + ", " + currentSetType + ")");
        return "";
    }

    @Override
    public String visitChangeSet(GymlangParser.ChangeSetContext ctx) {
        int setIndex = Integer.parseInt(ctx.oldSet().NUMBER().getText()) - 1;

        if (setIndex < 0 || setIndex >= currentSets.size()) {
            throw new RuntimeException("Error: Cannot change set at index " + setIndex + " (out of bounds)");
        }

        int weight = Integer.parseInt(ctx.newSet().NUMBER(0).getText());
        int reps = Integer.parseInt(ctx.newSet().NUMBER(1).getText());

        currentSets.set(setIndex, new SetData(weight, reps, currentSetType));
//        System.out.println("Changed set at index " + setIndex + " to Set(" + weight + ", " + reps + ", " + currentSetType + ")");
        return "";
    }

    // Weekly Plan
    @Override
    public String visitWeekPlan(GymlangParser.WeekPlanContext ctx) {
        visit(ctx.weekBody());
        return "";
    }

    @Override
    public String visitWeekBody(GymlangParser.WeekBodyContext ctx) {
        visit(ctx.days());
        return "";
    }

    @Override
    public String visitDays(GymlangParser.DaysContext ctx) {
        for (GymlangParser.DayAssignmentContext assignment : ctx.dayAssignment()) {
            visit(assignment);
        }
        return "";
    }

    @Override
    public String visitDayAssignment(GymlangParser.DayAssignmentContext ctx) {
        String day = ctx.NAME().getText();
        if (!currentExerciseList.containsKey(day)) {
            throw new RuntimeException("Error: " + day + " is not defined.");
        }

        List<ExerciseData> exercises = currentExerciseList.get(day);
        weeklyPlan.put(day, new ArrayList<>(exercises));

        return "";
    }

    // check plan
    private void checkWarnings() {
        int totalTime = 0;
        workoutVolume.clear();
        Map<String, List<String>> muscleToExercises = new HashMap<>();

        for (String day : weeklyPlan.keySet()) {
            for (ExerciseData ex : weeklyPlan.get(day)) {
                // collect volume and workout time
                totalTime += ex.getEstimatedTime();
                int volume = 0;
                for (SetData sets : ex.sets) {
                    volume += sets.getVolume();
                }
                for (String muscle : ex.targetMuscles) {
                    workoutVolume.put(muscle, workoutVolume.getOrDefault(muscle, 0) + volume);
                    muscleToExercises.computeIfAbsent(muscle, k -> new ArrayList<>()).add(ex.name);
                }

            }
            if (totalTime > timePerSession) {
                warnings.add("[Time too long]: " + day + " estimated " + totalTime + " minutes workout time.");
            }
        }

        for (String muscle : workoutVolume.keySet()) {

            int actualVolume = workoutVolume.get(muscle);
            if (!muscleVolumeGoal.containsKey(muscle)) {
                List<String> exercises = muscleToExercises.get(muscle);
                System.out.println("[Warning]: " + muscle + " involved in " + exercises + " but has no volume goal defined.");
                continue;
            }
            int targetVolume = muscleVolumeGoal.get(muscle);
//            System.out.println("checking " + muscle + ", volume = " + targetVolume);

            if (targetVolume > 0) {
                if (actualVolume > targetVolume ) {
                    warnings.add("[Volume too high]: " + muscle + " " + actualVolume + " (goal: " + targetVolume + ")");
                } else if (actualVolume < targetVolume) {
                    warnings.add("[Volume too low]: " + muscle + " " + actualVolume + " (goal: " + targetVolume + ")");
                }
            }
        }
    }

    private String generateWorkoutPlan() {
        StringBuilder sb = new StringBuilder();
        for (String day : weeklyPlan.keySet()) {
            sb.append(day).append(":\n");
            for (ExerciseData ex : weeklyPlan.get(day)) {
                sb.append("\t").append(ex.toString()).append("\n");
            }
        }
        for (String warning : warnings) {
            sb.append("\n").append(warning);
        }
        return sb.toString();
    }
    private void printWorkoutVolume() {
        System.out.println("Workout Volume Summary:");
        for (String muscle : workoutVolume.keySet()) {
            System.out.println(muscle + ": " + workoutVolume.get(muscle) + " total volume");
        }
        /*
        System.out.println("Volume Goals:");
        for (String muscle : muscleVolumeGoal.keySet()) {
            System.out.println(muscle + ": " + muscleVolumeGoal.get(muscle) + " total volume");
        }
         */
    }
    int evalExpr(String expr, int weight, int reps) {
        expr = expr.replace("weight", String.valueOf(weight))
                .replace("reps", String.valueOf(reps));
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return ((Number) engine.eval(expr)).intValue();
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating: " + expr);
        }
    }
}

// Helper Classes
class ExerciseData {
    String name;
    int restTime;
    List<SetData> sets;
    Set<String> targetMuscles;

    public ExerciseData(String name, int restTime, List<SetData> sets, Set<String> targetMuscles) {
        this.name = name;
        this.restTime = restTime;
        this.sets = sets;
        this.targetMuscles = targetMuscles;
    }

    public int getTotalSets() {
        return sets.size();
    }

    public int getEstimatedTime() {
        return restTime * sets.size() * 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "(" + restTime + " min): ");
        for (SetData set : sets) {
            sb.append(set.weight).append(" x ").append(set.reps).append(" | ");
        }
        return sb.substring(0, sb.length() - 3);
    }
}

class SetData {
    float weight;
    int reps;
    String type;

    public SetData(float weight, int reps, String type) {
        this.weight = weight;
        this.reps = reps;
        this.type = type;
    }

    public float getVolume(){
        return this.weight * this.reps;
    }
}

class DefinedSet {
    String name;
    List<SetExpression> expressions; // Set(weight + 20, reps - 2)

    DefinedSet(String name, List<SetExpression> expressions) {
        this.name = name;
        this.expressions = expressions;
    }
}

class SetExpression {
    float weight;
    int reps;

    SetExpression(float weight, int reps) {
        this.weight = weight;
        this.reps = reps;
    }
}