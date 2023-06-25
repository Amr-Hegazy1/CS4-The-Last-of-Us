
## The Last of Us: Legacy

## Team Members:
<a href="https://github.com/Amr-Hegazy1/CS4-The-Last-of-Us/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Amr-Hegazy1/CS4-The-Last-of-Us" />
</a>

## Game Description

This document provides a detailed description of the game elements and game play. It does not
contain any implementation or coding details.

# Intro

The Last of Us: Legacyis a single player survival game set in a zombie apocalyptic world.
The game is conducted in a turn based manner, in which each player character receives a specific
number of action points per turn, which they can use to move, attack or cure zombies, or use
special actions.

The player starts the game controlling only one hero, but can gain additional heroes by curing
zombies. The objective of the game for the player is to survive as long as it takes in order to
cure a sufficient number of zombies enough to build a community to survive the apocalypse.


# Characters

Characters in the game are split intoHeroesorZombies.

## Heroes

Heroes are the types of characters that the player can control. There are several types of heroes
available in the game, each one provides different assets for the player in order to win the game.
Any character; Zombie or Hero, has an initial amount of health that decreases whenever they
are attacked. If the character’s health ever reaches 0 they are killed and removed from the
game.

Considering that the aim of the game is to build a large enough community to survive the
apocalypse thus the player must try to maintain and protect their heroes at all costs, as well as
try to expand their available pool of heroes.

Each hero type has a unique action they can add to the player’s team:

- Explorer:Allows the player to be able to see the entirety of the map for 1 turn whenever
    a supply is used.
- Medic:Can heal and restore health to other heroes or themselves, each process of healing
    uses 1 supply.
- Fighter: Can attack as many times in a turn without costing action points, for 1 turn
    whenever a supply is used.

Possible actions that can be done by a character:

- Move
- Attack a zombie
- Cure a zombie
- Use their class dependant unique action

## Zombies

Zombies are the types of characters that threaten the player during the game. Zombies cannot
be controlled, however they can be cured or attacked. Each time a zombie is killed another
zombie will spawn somewhere on the map. In addition to extra zombies spawning every time
the player ends a turn.

Whenever a zombie is cured an extra hero will take its place and be available for the player to
use in future turns.

# Collectibles

Collectibles are scattered objects across the map that can help the player survive and advance
in the game. Each collectible is only usable once, and after that is discarded from the hero’s
inventory and cannot be reused.

- Vaccines: Vaccines are an integral and important part of the game. As the player can
    only win the game once all vaccines have been collected and used. Vaccines are also the
    only means through which players can cure zombies and recruit new heroes.


- Supplies: Supplies are the other type of collectible available in the game. Supplies
    enable the carrying hero to use their special action.

# Gameplay Flow

The player starts off in a 15x15 grid map with just one hero and 10 zombies. The player can
only see the directly adjacent cells next to their pool of heroes. The player then keeps taking
his turn trying to collect vaccines, and cure or kill zombies. The game ends when the player
has collected and used all vaccines or when all heroes have been overwhelmed and defeated by
the zombies.

The player only wins if he has successfully collected and used all vaccines and has 5 or more
heroes alive.


# How to play:
1. clone repo into your local directory
```
git clone https://github.com/Amr-Hegazy1/CS4-The-Last-of-Us.git
```
2. Go the views package

3. Run the Main.java file

* Note that you need to have JavaFX installed & loaded into VM arguments

* We are still working on creating a JAR file to avoid these issues