package com.marwinekk.armortrims.client;

import com.marwinekk.armortrims.ArmorTrimsMod;
import com.marwinekk.armortrims.ArmorTrimsModEntities;
import com.marwinekk.armortrims.client.renderer.BasicArrowRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

public class Client {

    public static void setup() {
        MinecraftForge.EVENT_BUS.addListener(Client::clientTick);
        MinecraftForge.EVENT_BUS.addListener(Client::login);
        MinecraftForge.EVENT_BUS.addListener(Client::onRenderPlayer);
    }

    public static void keybinds(RegisterKeyMappingsEvent event) {
        ModKeybinds.allKeybinds().forEach(event::register);
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ArmorTrimsModEntities.TNT_ARROW, BasicArrowRenderer::new);
    }

    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            ArmorTrimsModClient.clientTick(Minecraft.getInstance());
        }
    }

    public static void onRenderPlayer(RenderLivingEvent.Pre event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(MobEffects.INVISIBILITY)) {
            event.setCanceled(true);
        }
    }

    public static void login(ClientPlayerNetworkEvent.LoggingIn event) {
        ArmorTrimsMod.playerLogin(event.getPlayer());
    }
}
