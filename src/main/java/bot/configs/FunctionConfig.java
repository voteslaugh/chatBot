package bot.configs;

import components.TaskGenerator;

public class FunctionConfig {
    private TaskGenerator taskGenerator;

    public FunctionConfig(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    public TaskGenerator getTaskGenerator() {
        return taskGenerator;
    }
}
