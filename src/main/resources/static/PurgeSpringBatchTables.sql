-- Sequence of purging
select * from BATCH_JOB_EXECUTION_CONTEXT;
select * from BATCH_JOB_EXECUTION_PARAMS;
select * from BATCH_STEP_EXECUTION_CONTEXT;
select * from BATCH_STEP_EXECUTION;
select * from BATCH_STEP_EXECUTION_SEQ;
select * from BATCH_JOB_EXECUTION_SEQ;
select * from BATCH_JOB_SEQ;
select * from BATCH_JOB_EXECUTION;
select * from BATCH_JOB_INSTANCE;

-- Delete Statement provided below, use WHERE clause as necessary

delete from BATCH_JOB_EXECUTION_CONTEXT  where JOB_EXECUTION_ID in
                                               (  select distinct JOB_EXECUTION_ID from BATCH_JOB_EXECUTION) ;
delete from BATCH_JOB_EXECUTION_PARAMS where JOB_EXECUTION_ID in
                                             (   select distinct JOB_EXECUTION_ID from BATCH_JOB_EXECUTION ) ;
delete from BATCH_STEP_EXECUTION_CONTEXT where STEP_EXECUTION_ID in
                                               (  select distinct STEP_EXECUTION_ID from BATCH_STEP_EXECUTION where JOB_EXECUTION_ID in
                                                                                                                    (   select distinct JOB_EXECUTION_ID from BATCH_JOB_EXECUTION  )  ) ;

delete from BATCH_STEP_EXECUTION where JOB_EXECUTION_ID in (1,2)
delete from BATCH_STEP_EXECUTION_SEQ where ID=2
delete from BATCH_JOB_EXECUTION_SEQ where ID=2
delete from BATCH_JOB_SEQ where ID=2
delete from BATCH_JOB_EXECUTION where JOB_EXECUTION_ID in (1,2)
DELETE FROM BATCH_JOB_INSTANCE WHERE JOB_INSTANCE_ID in (1,2) 