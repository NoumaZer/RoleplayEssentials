package me.nouma.rpe.skript.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import me.nouma.rpe.jobs.Job;
import me.nouma.rpe.jobs.JobsHelper;

public class JobType {

    static {
        Classes.registerClass(new ClassInfo<Job>(Job.class, "job").user("job").name("job").parser(new Parser<Job>() {
            @Override
            public Job parse(String s, ParseContext parseContext) {
                Job job = JobsHelper.getJobFromName(s);
                return job;
            }

            @Override
            public String toString(Job job, int i) {
                return job.getRawName();
            }

            @Override
            public String toVariableNameString(Job job) {
                return job.getRawName();
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        }));
    }
}
