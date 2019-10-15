# FrioTime-Cards-Engine
#### FTCE is a core engine with tools to implement new card games with custom rules.

# In development!

## General Information
This project is going to be a core for card games to be easily implemented without the game creator to worry about drawing things to the screen or deal with the transfer of memory during gameplay. This project will also provide a front-end for a general user to view all the games they have downloaded, launch them, and possibly play online.

## User Information
The state of the project provides no implementation of any featues for the user. Once the project is in a working state the user should be able to select from a list of games they have downloaded and play either by themselves or with a friend.

## Core / Project Developmental Information
Currently the project is under construction. There is some basic functionality implemented but, nothing related to actually creating games using this tool. Once a majority of the backbone pieces have been developed the focus will change to putting them together to bring the project to a useable state. After the project has been put into a working condition, there will be aditional changes to include more functionality for game developers.

## Game Developer Information
At the moment the project is not at a working state for game developers. The plan is to have the game developer extend a Game class provided. This subclass will contain all the logic for the game and can talk to the engine with a provided gameState. The gameState will contain functionality to ask the engine for more cards, allow a user to take a turn, check what card was played to calculate logic, etc. Once the project moves towards a working state, more information on how to get started developing game will be provided.
