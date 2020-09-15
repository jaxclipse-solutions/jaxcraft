package org.jaxclipse.jaxcraft.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jaxclipse.jaxcraft.command.CommandProvider;
import org.jaxclipse.jaxcraft.model.InventoryContainer;
import org.jaxclipse.jaxcraft.model.InventoryContainerImpl;
import org.jaxclipse.jaxcraft.model.JaxcraftContext;
import org.jaxclipse.jaxcraft.model.JaxcraftWorldMap;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertTrue;

public class FullGameTest {

    @Test
    public void testFullWalkThrough() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        JaxcraftWorldMap gameModel = mapper.readValue(new File("src\\main\\resources\\world.json"),
                JaxcraftWorldMap.class);

        JaxcraftContext context = new JaxcraftContext(gameModel);

        Game game = new GameImpl();

        //   JaxcraftConsole console = mock(JaxcraftConsole.class);

        JaxcraftConsole console = new FakeConsole();
        game.setConsole(console);
        game.init(context);

        boolean r1 = game.handleInput("n");
        //    verify(console, times(1)).consolePrint();

        assertTrue(game.handleInput("take torch"));
        assertTrue(game.handleInput("turnon torch"));
        assertTrue(game.handleInput("n"));
        assertTrue(game.handleInput("open chest"));
        assertTrue(game.handleInput("take explosive"));
        assertTrue(game.handleInput("n"));
        assertTrue(game.handleInput("turnon explosive"));
        assertTrue(game.handleInput("attack gnome with explosive"));
        assertTrue(game.handleInput("take key"));
        assertTrue(game.handleInput("put key lock"));
        assertTrue(game.handleInput("n"));

    }

    class FakeConsole implements JaxcraftConsole {

        @Override
        public void setShowPrompt(boolean showPrompt) {

        }

        @Override
        public String getLineInput() {
            return null;
        }

        @Override
        public String getLineInput(String promptTextOverride) {
            return null;
        }

        @Override
        public void consolePrint(Collection<String> messages) {

        }

        @Override
        public void consolePrint(String... messages) {
          //  System.out.println("---------------------");
            for (String message :
                    messages) {
                System.out.println("::::junit:::::" + message);
            }
        }
    }
}
