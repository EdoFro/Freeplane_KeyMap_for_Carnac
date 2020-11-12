# Freeplane_KeyMap_for_Carnac
 Groovy script to create an yml file containing the actual key shortcuts of Freeplane to be used with Carnac

---

## About Freeplane

[Freeplane](https://www.freeplane.org/wiki/index.php/Home) is a free and open source software application that supports thinking, sharing information and getting things done at work, in school and at home. The software can be used for [mind mapping](https://secure.wikimedia.org/wikipedia/en/wiki/Mind_map) and analyzing the information contained in mind maps. Freeplane runs on any operating system that has a current version of Java installed. It can be run locally or [portably](https://en.wikipedia.org/wiki/Portable_application) from removable storage like a USB drive.

![Freeplane example](https://www.freeplane.org/wiki/images/1/12/FreeplaneWiki.jpg)

---

## About Carnac - 'the Magnificent Keyboard Utility'

[Carnac](http://carnackeys.com/) is a keyboard logging and presentation utility for presentations, screencasts, and to help you become a better keyboard user. (As far as I know it is Windows only)

[Carnac in code52.org](http://code52.org/carnac/)

[Carnac in GitHub](https://github.com/Code52/carnac)

![Carnac screenshot](https://code52.org/carnac/screenshot.png)

![Carnac example](http://carnackeys.com/images/screenshot.gif)

---

## About Freeplane_KeyMap_for_Carnac

The script in this repository takes the information about the key accelerators that are active in **your** Freeplane application and save them as an **.yml** file that can be used by Carnac.

The file is saved in the same folder where the script is.

In the same folder must be copied the **keysDict.txt** file. It is used by the script to translate some keys from the Freeplane denomination to Carnac's.

The information read by the script is the same you can see in Freeplane in the "**Hot keys table**" that you can access through **Help/Key Reference**, which contains standard and custom key combinations.

This new file (Freeplane.yml) has to be then manually copied to the **Carnac\Keymaps** directory

I made the Freeplane.yml file in this repository with this script, so, it contains the key combination I personally use in Freeplane.

## Motivation

I want to do some videos about using some Freeplane features and came to Carnac as an option to show the KeyStrokes I use while using Freeplane.

What I liked about Carnac was that it also shows which action the keystroke is calling (Maybe there are better application for this but I didn't look further).

I made this as a script because key combinations in Freeplane can easily be changed and can be very different between different users.

I'm learning how to do scripts in Groovy for Freeplane and it seamed as a nice challenge to excercise with.

I think it's a script that can be easily adapted for other applications (if needed)

Look at the script itself for further information.
