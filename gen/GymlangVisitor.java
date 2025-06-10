// Generated from /Users/minlee/Desktop/SCHOOL/SPRING25/Lang Structure/ProjectGymLang/P03_DavidLee/ANTLR/Gymlang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GymlangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GymlangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GymlangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(GymlangParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#muscleGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuscleGroup(GymlangParser.MuscleGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#muscleList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuscleList(GymlangParser.MuscleListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#gym}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGym(GymlangParser.GymContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#exerciseDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExerciseDefinition(GymlangParser.ExerciseDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#defSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefSet(GymlangParser.DefSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#setDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDef(GymlangParser.SetDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#setLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetLine(GymlangParser.SetLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#setDefExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDefExpr(GymlangParser.SetDefExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#editSetDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEditSetDef(GymlangParser.EditSetDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(GymlangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#progGoal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgGoal(GymlangParser.ProgGoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#sessionLen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionLen(GymlangParser.SessionLenContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#daysPerWeek}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDaysPerWeek(GymlangParser.DaysPerWeekContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#volumeGoal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVolumeGoal(GymlangParser.VolumeGoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#muscleVolume}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuscleVolume(GymlangParser.MuscleVolumeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#dayPlan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDayPlan(GymlangParser.DayPlanContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#dayBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDayBody(GymlangParser.DayBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#exercise}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExercise(GymlangParser.ExerciseContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#restTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestTime(GymlangParser.RestTimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#sets}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSets(GymlangParser.SetsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(GymlangParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#predefSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredefSet(GymlangParser.PredefSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#editSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEditSet(GymlangParser.EditSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#editAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEditAction(GymlangParser.EditActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#removeSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveSet(GymlangParser.RemoveSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#addSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSet(GymlangParser.AddSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#changeSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeSet(GymlangParser.ChangeSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#oldSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOldSet(GymlangParser.OldSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#newSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewSet(GymlangParser.NewSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#weekPlan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeekPlan(GymlangParser.WeekPlanContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#weekBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeekBody(GymlangParser.WeekBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#days}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDays(GymlangParser.DaysContext ctx);
	/**
	 * Visit a parse tree produced by {@link GymlangParser#dayAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDayAssignment(GymlangParser.DayAssignmentContext ctx);
}