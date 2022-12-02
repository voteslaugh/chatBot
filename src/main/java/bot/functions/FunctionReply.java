package bot.functions;

import java.util.List;

public class FunctionReply {
    private boolean isFinishedWork = false;
    private Data data;

    public boolean isFinishedWork() {
        return isFinishedWork;
    }

    public void setFinishedWork(boolean finishedWork) {
        isFinishedWork = finishedWork;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
