# Illegal Aliens
This is the Readme for the application Illegal Aliens (https://github.com/Coec0/tda367-JOKE)

@author Emil Josefsson
![Logo](https://github.com/Coec0/tda367-JOKE/blob/master/android/assets/logo.png)

## Project Course: TDA367, Chalmers University of Technology
Contributors:
- Oscar Carlsson: (Coec0)
- Emil Josefsson: (josefssonemil)
- Kevin Rudnick: (96kevrud)
- Johan Svennungsson: (knya)

## Contents
1. Introduction
2. Documents
3. Game Description
    1. Political system
    2. Executive orders
    3. Presidental powers
    4. Towers
    5. Tower upgrades
4. Project Structure
5. Necessary (circular) dependencies
6. Current flaws
7. Buglist (known)
8. Future goals

## Introduction
This Readme aims to fulfill three purposes:
- provide information about the game (as a complement to the information available in-game)
- provde information about the system structure
- provide information about flaws

Run the game by either downloading the source code and launching it via an IDE or Terminal or by downloading
the JAR file (coming later).

The game was developed by four students at Chalmers University of Technology as an object oriented project in
the course **TDA367**. The course aims to develop an application with the MVC design pattern as core and in an
object oriented way. Course page can be found [here](http://www.cse.chalmers.se/edu/year/2017/course/tda367/).

Application was implemented using the [LibGDX](http://libgdx.badlogicgames.com) as framework, Git as versioning tool and Gradle as dependency handler.

## Documents
Documents, such as meetings reports, report, SDD and RAD are found in the docs folder.

## Game Description
Illegal Aliens is a Tower Defence game. Your job is to the defend the White House from invading aliens, with aid from
towers, executive orders and presidental powers (super powers).

### Game Description: Political system
The game is based on a political system. Depending on which towers you decide to buy, you gain a number of politcal points
and votes for the party (republican or democrat) the tower belongs to. When you have accumulated enough points, you can use
presidental powers. These are strong and expensive powers which will help you when in trouble.

### Game Description: Executive Orders
Executive orders are tricky. The special thing about them is that there is always a drawback for the benefits. There are six
executive orders, three for each political side. When used, you gain a large amount of points/votes for the corresponding party.
Available executive orders:
- Civil War (republican): Removes all democrat towers and gives a large number of political points
- Civil War (democrat): Removes all republican towers and gives a large number of politcal points
- Declare War: Gives instant money, but purchasing towers are more expensive for a few rounds
- Open Borders: More aliens are spawned in the coming waves (possibly more income)
- Obama Care: Towers are more expensive but gain increased stats
- Tax Cut: Towers are cheaper but gain lowered stats

### Game Description: Presidental powers (Super powers)
As mentioned when describing the political system, the presidental powers are very strong and are purchased with
politcal points. These can only be used when you have accumulated enough votes for a specific party.

Available presidental powers:
- Nuke (Harry S. Truman, Democrat): Kills all enemies on the map
- Wall (Donald Trump, Republican): Gives you the option to place a wall to block a path
- Towerboost (Theodore Roosevelt, Republican): Temporarily boosts all your placed towers
- Minutemen (George Washington, Democrat): Temporary towers spawn on a fixed position during a round to aid you

*Note: George Washington was independent in real life, only seen as a democrat in our game.*

### Game Description: Towers
Towers are pretty straightforward. You purchase them and put them on the map, and they automatically attacks the enemies. The
only exception is Riot Shield, as it doesnt attack and damage enemies. Instead, they push back nearby enemies (think of it
as a delay mechanism). You can also set their targeting state (e.g target first, target last etc).

Available towers:
- Soldier: Weak, regular tower
- Ranger: Basically a stronger soldier
- Tank: High damage, high range, low rate of fire and area of effect missiles
- Riot Shield: Pushes back nearby enemies
- Net Gunner: Permamently slowing hit enemies with a net
- Sniper: Infinite range and high bullet speed

#### Game Description: Tower upgrades
Towers can be upgraded. As of now, the radius, damage and cooldown can be upgraded for in-game currency (money).

*Note: The upgrade cost scales with the tower value; a stronger tower equals more expensive upgrades.*

### Game Description: Enemies
Enemies spawn and travel via the roads to the White House. They automatically finds the shortest road available from their
spawn position.

Attacking enemies:
- Alien: The most simple and weak enemy
- Alien with helmet: An alien with more health compared to the regular one
- Sneaky alien: Small and fast alien
- High alien: These aliens ignore potential walls on the map
- Tough alien: A very strong but also very slow enemy

## Project structure in GitHub
- **android**: contains android specific code as well as the assets folder containing all textures/images used in the game
- **core**: contains core project files
- **desktop**: contains desktop specific code and desktop launcher
- **docs**: contains weekly meetings, report, sdd and rad
- **gradle/wrapper**: dependency handling
- **html**: HTML specific code
- **ios**: iOS specific code

All game code is located in the sub folders of the core folder. Images, textures and so on is located in the assets folder in
the android hierchary.


## Necessary (circular) dependencies
One of the goals during development has been to minimize all kind of unnecessary dependencies, by analyzing with STAN. Sadly, one circular dependency cannot be removed since it has its roots in the framework itself (LibGDX). The affected classes are **Screen** and **Game**. More information about the unremovable (without breaking the game) dependency can be found [here](https://gamedev.stackexchange.com/questions/67232/how-to-remove-a-circular-dependency-as-pointed-out-in-a-libgdx-tutorial).

## Current flaws
- UI is not particulary user friendly
- The game descriptions in this readme should also be available as information in-game
- Game has to be further balanced
## Buglist (known)
- Tower can be upgraded while having it highlighted in-before purchasing it. Clicking the upgrade button causes a weird interaction; the tower is placed behind the UI (caused by not disabling UI during the time the tower is highlighted and stuck to the mouse)
- When clicking on a tower on the right stage panel causes the tower to appear in the bottom left corner of the screen (caused by default position)
- Walls are glitched on map 2 (caused by hardcoded map nodes)

## Future goals
- More balancing (high priority)
- Provide more information and help inside the game (high priority)
- Clean up the User Interface (high/medium priority)
- Add particle effects (medium priority)
- Add sound effects (low priority)
