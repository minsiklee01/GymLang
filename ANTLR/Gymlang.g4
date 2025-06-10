grammar Gymlang;

//stmt : muscleGroup, gym,  START_PROGRAM ID block END;
stmt : muscleGroup gym  defSet START_PROGRAM NAME block END;
muscleGroup: MUSCLE_GROUP OCB muscleList CCB
           |;
muscleList : NAME (COMMA NAME)* COMMA? ;
gym: GYM NAME OCB exerciseDefinition (COMMA exerciseDefinition)* COMMA? CCB
   |;
exerciseDefinition : NAME OP muscleList CP ;

defSet: (DEFSET NAME OP WEIGHT COMMA REPS CP OCB setDef CCB)*;
setDef: setLine+;
setLine: SET OP setDefExpr COMMA setDefExpr CP;
setDefExpr: WEIGHT editSetDef?
          | REPS editSetDef?;
//(SET OP WEIGHT editSetDef? COMMA REPS editSetDef? CP)+;
editSetDef: PLUS NUMBER
          | DASH NUMBER;
block : progGoal dayPlan weekPlan;

// Program Goals
progGoal : sessionLen daysPerWeek volumeGoal;
sessionLen : TPS EQUAL NUMBER;
daysPerWeek : DPW EQUAL NUMBER;
volumeGoal : muscleVolume+;
muscleVolume : NAME VOLUME EQUAL NUMBER;

// Daily Plan
dayPlan : (NAME OCB dayBody CCB)+;
dayBody : exercise+ ;
exercise : NAME DOT NAME restTime COL sets;
restTime : REST NUMBER MIN;

// Workout Set Definition
sets : (set | predefSet)+;
set : SET OP NUMBER COMMA NUMBER CP;
predefSet : USE SET DOT NAME OP NUMBER COMMA NUMBER CP editSet;
editSet : COL editAction+ |;
editAction : removeSet
           | addSet
           | changeSet;
removeSet : DSET NUMBER;
addSet : ASET OP NUMBER COMMA NUMBER CP;
changeSet : CHANGE oldSet TO newSet;
oldSet : SET NUMBER;
newSet : SET OP NUMBER COMMA NUMBER CP;

// Weekly Plan
weekPlan : WEEK weekBody;
weekBody : OCB days CCB;
days : dayAssignment (COMMA dayAssignment)* COMMA?;
dayAssignment : NAME NUMBER?;

COMMENT : '//' ~[\r\n]* -> skip ;
MULTILINE_COMMENT : '/*' .*? '*/' -> skip ;

EQUAL: '=';
OCB : '{';
CCB : '}';
OP : '(';
CP : ')';
DOT : '.';
COMMA : ',';
COL : ':';
DASH : '-';
PLUS : '+';

MUSCLE_GROUP : 'MuscleGroup';
GYM : 'Gym';
START_PROGRAM : 'start program';
END : 'end';
TPS : 'timePerSession';
DPW : 'daysPerWeek';
VOLUME: 'volume';
REST : 'rest';
SETS : 'sets';
SET : 'Set';
MIN : 'minutes';
USE : 'use';
DSET : '- Set';
ASET : '+ Set';
CHANGE : 'change';
TO : 'to';
WITH : 'with';
WEEK : 'Week';
DAY : 'Day';
DEFSET : 'defineSet';
WEIGHT : 'weight';
REPS : 'reps';


fragment CLETTER : [A-Z];
fragment LETTER: [a-zA-Z];
fragment NDIGIT : [1-9];
fragment DIGIT : [0-9];

NAME : CLETTER LETTER*;
NUMBER : NDIGIT DIGIT*;

WS : [ \t\r\n]+ -> skip ;