package ActionCommands;

import com.company.SavedData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActionClearDataTest {

    @Test

    void successfulDeletion(){


      ActionCommand command =   new ActionClearData(SavedData.getLastTheme());

        assertDoesNotThrow(command::execute);

    }

}