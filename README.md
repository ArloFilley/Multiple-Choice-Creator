<h1 align='center'>Multiple-Choice-Creator</h1>

This is an awesome program developed by:

- Arlo Filley
 
<h2 align='left'>Built Using</h2>
<ul>
	<li>Java
</ul>


<h2 align='left'>Functionality</h2>

This program uses a number of text files to determine the choices that the player can make, their stats, and possible enemies for them to fight.


Some features of this program include
- custom flags for making writing easy
- interpreter for turning text files into gameplay
- endgames, and restarting
- fights, with different enemy types
- level ups, with stats being updated and stored
  

<h2 align='left'>Flags, Objects, & More</h2>

<h3>Story.txt Flags:</h3>
<ol>
	<li>NEWCHOICE<br>(Marking)<br>This tells the interpreter when a new choice is available<br>
  <li>FIGHT<br>(Enemy)<br>This starts a fight with an enemy type (so long as it is valid and defined in Enemies.txt<br>
  <li>GAMEOVER<br>Brings the game to an end with the player having the option to restart<br>
  <li>I<br>Used to indicate the Intro to the story, which should be read before anything else
  <li>GOTO (Marking)<br>The interpreter will send the user to whichever marking you tell it, so long as it is valid
  <li>A:<br>Used to mark out the choices that the user can make
  <li>FILEOVER<br>Used to mark out the end of the file to the interpreter, anything beyond this line will not be read
</ol>

<h3>Player (Stats.txt) Object:</h3>
<ol>
	<li>Health
  <li>Attack
	<li>Defence
	<li>Experience (max of 99 before level up)
</ol>
  
<h3>Enemy (Enemies.txt) Object:</h3>
<ol>
  <li>Name
  <li>Health
  <li>Attack
  <li>Defence
  <li>Experience (max of 99 before level up)
</ol>

<h3>Fighter.java:</h3>
This creates a fighter object used for both the player and enemies

<h2 align='left'>Development</h2>
Development of this project is now done (I actually finished this months before uploading it). It has a number of technical and design faults however in most cases it works to do its job. Any new player can pick this up and have some fun. I hope you enjoy, and maybe someday I'll fix the mess of spaghetti code that I've made. :)
