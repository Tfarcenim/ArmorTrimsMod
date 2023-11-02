package com.marwinekk.armortrims.util;

import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public class ArmorTrimAbility {

    public final Consumer<ServerPlayer> onEquip;
    public final Consumer<ServerPlayer> onServerPlayerTick;
    public final Consumer<ServerPlayer> activateCombatAbility;
    public final Consumer<ServerPlayer> onRemove;

    public final int activeTicks;
    public final int cooldown;


    public ArmorTrimAbility(Consumer<ServerPlayer> onEquip, Consumer<ServerPlayer> onServerPlayerTick,
                            Consumer<ServerPlayer> activateCombatAbility, Consumer<ServerPlayer> onRemove) {
        this(onEquip,onServerPlayerTick,activateCombatAbility,onRemove,0, 20 * 20);
    }

    public ArmorTrimAbility(Consumer<ServerPlayer> onEquip, Consumer<ServerPlayer> onServerPlayerTick,
                            Consumer<ServerPlayer> activateCombatAbility, Consumer<ServerPlayer> onRemove, int activeTicks, int cooldown) {
        this.onEquip = onEquip;
        this.onServerPlayerTick = onServerPlayerTick;
        this.activateCombatAbility = activateCombatAbility;
        this.onRemove = onRemove;
        this.activeTicks = activeTicks;
        this.cooldown = cooldown;
    }
}
