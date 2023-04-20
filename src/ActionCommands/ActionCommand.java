package ActionCommands;

public interface ActionCommand {


    /**
     * Command executed.
     */
    void execute();

    /**
     * Message displayed on ActionBoxes.
     * @return message
     */
    String message();


}
