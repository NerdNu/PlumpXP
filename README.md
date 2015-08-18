# PlumpXP

A fairly simple bukkit plugin to achieve a few odds and ends:

1. Multiply XP drops from all mobs (=> reduce XP farming)
2. Multiply XP drops from blazes (=> make blaze farms less OP)
3. Multiply XP drops from ore blocks (=> reduce XP mining farming)
4. Change player XP drops to be a fraction of total XP (=> rather than being limited/derpy)

##Config

* plump-multiplier - multiplies all mob XP drops
* blaze-multiplier - multiplies blaze XP drops. NB: also multiplied by first field; use 0.5 to bring in line with other hostile mobs
* player-override - whether or not to override vanilla player xp drop behaviour
* player-multiplier - multiplier for player XP drops. A value of 1.0 will transfer all XP on player death
* ore-multiplier - multiplies ore block XP drops.
* furnace-multiplier - multiplies XP dropped when collecting smelted items.
* fishing-multiplier - multiplies XP dropped when fishing
* plump-outside-radius - multiplies XP only when the player is further than a certain distance from spawn
  * enabled — true/false
  * radius - distance from spawn to disable the plump
  * centerpoint - the spawn point, in the form of [x, z]
  * worlds - list of worlds to enable the plump in

##Commands

Players with the plumpxp.reload permission can use /reloadplumpxp to reload config.
