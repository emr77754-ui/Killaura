package com.example.killaura;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.lwjgl.glfw.GLFW;

public class KillAuraMod implements ClientModInitializer {
    public static KillAuraModule MODULE = new KillAuraModule();

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(MODULE::tick);
        // Right Shift ile menü açma
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.getWindow() == null) return;
            boolean shift = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            if (shift && client.currentScreen == null && !shiftPressed) {
                client.setScreen(new KillAuraScreen());
            }
            shiftPressed = shift;
        });
    }
    private boolean shiftPressed = false;
}
