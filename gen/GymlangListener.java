// Generated from /Users/minlee/Desktop/SCHOOL/SPRING25/Lang Structure/ProjectGymLang/P03_DavidLee/ANTLR/Gymlang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GymlangParser}.
 */
public interface GymlangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GymlangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(GymlangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(GymlangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#muscleGroup}.
	 * @param ctx the parse tree
	 */
	void enterMuscleGroup(GymlangParser.MuscleGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#muscleGroup}.
	 * @param ctx the parse tree
	 */
	void exitMuscleGroup(GymlangParser.MuscleGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#muscleList}.
	 * @param ctx the parse tree
	 */
	void enterMuscleList(GymlangParser.MuscleListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#muscleList}.
	 * @param ctx the parse tree
	 */
	void exitMuscleList(GymlangParser.MuscleListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#gym}.
	 * @param ctx the parse tree
	 */
	void enterGym(GymlangParser.GymContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#gym}.
	 * @param ctx the parse tree
	 */
	void exitGym(GymlangParser.GymContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#exerciseDefinition}.
	 * @param ctx the parse tree
	 */
	void enterExerciseDefinition(GymlangParser.ExerciseDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#exerciseDefinition}.
	 * @param ctx the parse tree
	 */
	void exitExerciseDefinition(GymlangParser.ExerciseDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#defSet}.
	 * @param ctx the parse tree
	 */
	void enterDefSet(GymlangParser.DefSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#defSet}.
	 * @param ctx the parse tree
	 */
	void exitDefSet(GymlangParser.DefSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#setDef}.
	 * @param ctx the parse tree
	 */
	void enterSetDef(GymlangParser.SetDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#setDef}.
	 * @param ctx the parse tree
	 */
	void exitSetDef(GymlangParser.SetDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#setLine}.
	 * @param ctx the parse tree
	 */
	void enterSetLine(GymlangParser.SetLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#setLine}.
	 * @param ctx the parse tree
	 */
	void exitSetLine(GymlangParser.SetLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#setDefExpr}.
	 * @param ctx the parse tree
	 */
	void enterSetDefExpr(GymlangParser.SetDefExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#setDefExpr}.
	 * @param ctx the parse tree
	 */
	void exitSetDefExpr(GymlangParser.SetDefExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#editSetDef}.
	 * @param ctx the parse tree
	 */
	void enterEditSetDef(GymlangParser.EditSetDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#editSetDef}.
	 * @param ctx the parse tree
	 */
	void exitEditSetDef(GymlangParser.EditSetDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GymlangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GymlangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#progGoal}.
	 * @param ctx the parse tree
	 */
	void enterProgGoal(GymlangParser.ProgGoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#progGoal}.
	 * @param ctx the parse tree
	 */
	void exitProgGoal(GymlangParser.ProgGoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#sessionLen}.
	 * @param ctx the parse tree
	 */
	void enterSessionLen(GymlangParser.SessionLenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#sessionLen}.
	 * @param ctx the parse tree
	 */
	void exitSessionLen(GymlangParser.SessionLenContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#daysPerWeek}.
	 * @param ctx the parse tree
	 */
	void enterDaysPerWeek(GymlangParser.DaysPerWeekContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#daysPerWeek}.
	 * @param ctx the parse tree
	 */
	void exitDaysPerWeek(GymlangParser.DaysPerWeekContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#volumeGoal}.
	 * @param ctx the parse tree
	 */
	void enterVolumeGoal(GymlangParser.VolumeGoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#volumeGoal}.
	 * @param ctx the parse tree
	 */
	void exitVolumeGoal(GymlangParser.VolumeGoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#muscleVolume}.
	 * @param ctx the parse tree
	 */
	void enterMuscleVolume(GymlangParser.MuscleVolumeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#muscleVolume}.
	 * @param ctx the parse tree
	 */
	void exitMuscleVolume(GymlangParser.MuscleVolumeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#dayPlan}.
	 * @param ctx the parse tree
	 */
	void enterDayPlan(GymlangParser.DayPlanContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#dayPlan}.
	 * @param ctx the parse tree
	 */
	void exitDayPlan(GymlangParser.DayPlanContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#dayBody}.
	 * @param ctx the parse tree
	 */
	void enterDayBody(GymlangParser.DayBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#dayBody}.
	 * @param ctx the parse tree
	 */
	void exitDayBody(GymlangParser.DayBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#exercise}.
	 * @param ctx the parse tree
	 */
	void enterExercise(GymlangParser.ExerciseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#exercise}.
	 * @param ctx the parse tree
	 */
	void exitExercise(GymlangParser.ExerciseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#restTime}.
	 * @param ctx the parse tree
	 */
	void enterRestTime(GymlangParser.RestTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#restTime}.
	 * @param ctx the parse tree
	 */
	void exitRestTime(GymlangParser.RestTimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#sets}.
	 * @param ctx the parse tree
	 */
	void enterSets(GymlangParser.SetsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#sets}.
	 * @param ctx the parse tree
	 */
	void exitSets(GymlangParser.SetsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(GymlangParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(GymlangParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#predefSet}.
	 * @param ctx the parse tree
	 */
	void enterPredefSet(GymlangParser.PredefSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#predefSet}.
	 * @param ctx the parse tree
	 */
	void exitPredefSet(GymlangParser.PredefSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#editSet}.
	 * @param ctx the parse tree
	 */
	void enterEditSet(GymlangParser.EditSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#editSet}.
	 * @param ctx the parse tree
	 */
	void exitEditSet(GymlangParser.EditSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#editAction}.
	 * @param ctx the parse tree
	 */
	void enterEditAction(GymlangParser.EditActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#editAction}.
	 * @param ctx the parse tree
	 */
	void exitEditAction(GymlangParser.EditActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#removeSet}.
	 * @param ctx the parse tree
	 */
	void enterRemoveSet(GymlangParser.RemoveSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#removeSet}.
	 * @param ctx the parse tree
	 */
	void exitRemoveSet(GymlangParser.RemoveSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#addSet}.
	 * @param ctx the parse tree
	 */
	void enterAddSet(GymlangParser.AddSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#addSet}.
	 * @param ctx the parse tree
	 */
	void exitAddSet(GymlangParser.AddSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#changeSet}.
	 * @param ctx the parse tree
	 */
	void enterChangeSet(GymlangParser.ChangeSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#changeSet}.
	 * @param ctx the parse tree
	 */
	void exitChangeSet(GymlangParser.ChangeSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#oldSet}.
	 * @param ctx the parse tree
	 */
	void enterOldSet(GymlangParser.OldSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#oldSet}.
	 * @param ctx the parse tree
	 */
	void exitOldSet(GymlangParser.OldSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#newSet}.
	 * @param ctx the parse tree
	 */
	void enterNewSet(GymlangParser.NewSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#newSet}.
	 * @param ctx the parse tree
	 */
	void exitNewSet(GymlangParser.NewSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#weekPlan}.
	 * @param ctx the parse tree
	 */
	void enterWeekPlan(GymlangParser.WeekPlanContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#weekPlan}.
	 * @param ctx the parse tree
	 */
	void exitWeekPlan(GymlangParser.WeekPlanContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#weekBody}.
	 * @param ctx the parse tree
	 */
	void enterWeekBody(GymlangParser.WeekBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#weekBody}.
	 * @param ctx the parse tree
	 */
	void exitWeekBody(GymlangParser.WeekBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#days}.
	 * @param ctx the parse tree
	 */
	void enterDays(GymlangParser.DaysContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#days}.
	 * @param ctx the parse tree
	 */
	void exitDays(GymlangParser.DaysContext ctx);
	/**
	 * Enter a parse tree produced by {@link GymlangParser#dayAssignment}.
	 * @param ctx the parse tree
	 */
	void enterDayAssignment(GymlangParser.DayAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GymlangParser#dayAssignment}.
	 * @param ctx the parse tree
	 */
	void exitDayAssignment(GymlangParser.DayAssignmentContext ctx);
}