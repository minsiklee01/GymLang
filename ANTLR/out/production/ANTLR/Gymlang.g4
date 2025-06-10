grammar Gymlang;

stmt : START_PROGRAM ID block END;
block : progGoal dayPlan weekPlan;

// Program Goals
progGoal : sessionLen daysPerWeek volumeGoal;
sessionLen : TPS EQUAL SESSLEN;
daysPerWeek : DPW EQUAL WNW;
volumeGoal : muscleVolume+;
muscleVolume : MUSCLE VOLUME EQUAL VOL;

// Daily Plan
dayPlan : (DAYNAME dayBody)+;
dayBody : OCB exercise CCB;
exercise : LIBRARY DOT WORKOUT restTime COL SETS
         | WORKOUT restTime COL SETS;
restTime : REST NUM MIN;

// Workout Set Definition
sets : (set | predefSet)+;
set : SET OP NUM COMMA NUM CP;
predefSet : USE SET DOT SETTYPE COL editSet;
editSet : editAction |;
editAction : (DSET NUM COL
           | ASET NUM COL
           | changeSet)+;
changeSet : CHANGE oldSet TO newSet;
oldSet : SET NUM;
newSet : SET OP WEIGHT COMMA REPS CP;

// Weekly Plan
weekPlan : WEEK NUM DASH NUM weekBody;
weekBody : OCB days CCB;
days : dayAssignment+;
dayAssignment : DAY NUM EQUAL DAYNAME;

WNW : [1-9][0-9]*;
SESSLEN : [1-9][0-9]*;
MUSCLE : [A-Z][a-z]*;
VOL : [1-9][0-9]*;
WEIGHT : [1-9][0-9]*;
REPS : [1-9][0-9]*;
NUM : [1-9][0-9]*;
DAYNAME : [A-Z][a-zA-Z]*;
ID : [A-Z][a-zA-Z]* ;
WORKOUT : [A-Z][a-zA-Z]*;
LIBRARY : [A-Z][a-zA-Z]*;
SETTYPE : [A-Z][a-zA-Z]* '_set';

START_PROGRAM : 'start program';
END : 'end';
TPS : 'timePerSession';
DPW : 'daysPerWeek';
VOLUME: 'volume';

EQUAL: '=';
OCB : '{';
CCB : '}';
OP : '(';
CP : ')';
DOT : '.';
COMMA : ',';
COL : ':';
DASH : '-';

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
WEEK : 'week';
DAY : 'day';
WS : [ \t]+ -> skip ;