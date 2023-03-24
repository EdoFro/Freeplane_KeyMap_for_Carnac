// @ExecutionModes({ON_SINGLE_NODE})


// texto: string variable that will contain the whole text of the file to be saved
texto = new StringBuilder()
texto << "group:          freeplane\nprocess:        javaw\n\n# Reference: Freeplane_KeyMap_for_Carnac\n\nshortcuts:\n"

// menu: defines which menu will be extracted ("main_menu" for all the menus)
def menu = "main_menu"

// gets the keyStrokes as a MenuEntry Map 
// http://www.freeplane.org/doc/api/org/freeplane/core/util/MenuUtils.MenuEntry.html
def keyStrokes = menuUtils.createAcceleratebleMenuEntryTree(menu)

/*
// scriptDir2: the path where this script is saved
def scriptDir2 = new File(this.class.protectionDomain.codeSource.location.path).parent
// loads translationMap with pairs in keysDict.txt
translationMap = getMapFromFile("${scriptDir2}/keysDict.txt")
//defines .yml file
def ymlFile = new File("${scriptDir2}/freeplane.yml")
*/

// get files
def ymlFile = node.find{it.text == 'freeplane.yml'}[0]?.link?.file
def keysDictFile = node.find{it.text =="keysDict.txt"}[0]?.link?.file

// loads translationMap with pairs in keysDict.txt
translationMap = getMapFromFile(keysDictFile)

// executes method wich goes through the keyStrokes MenuEntry Map and appends the information in 'texto'
caso (keyStrokes)

// saves the information to the freeplane.yml file
ymlFile.text = texto

// it also shows this info in a new node's note
node.createChild("result").note = texto
c.statusInfo = "freeplane.yml updated with actual keymap"


// ----methods---------------
// recursivelly goes through the MenuItem tree
    // it uses the formatCarnac function to get from the Freeplane to the Carnac format
def caso(n){
    if(n.leaf){
        def menuEntry = n.getUserObject()
        texto <<  "    - name: ${formatName(menuEntry.toString())}"  <<"\n"
        << "      keys:"  <<"\n"
        <<  "      - ${formatCarnac(menuEntry.getKeyStroke())}"  <<"\n"
     }
    n.children.each{
        caso(it )
    }
}

def formatCarnac(k){
    //gets a list of the keys in the string and format them 
    def teclas = k.toString().replace("pressed ","").toLowerCase().split(" ")*.capitalize() 
    //in a new list puts the keys ordered and translated in the Carnac's way 
    def tec2a =  [] << (teclas.size()>=2?teclas.size() == 2?teclas[0]:reordena(teclas[0 .. -2]):[]) << traduce(teclas[-1])
    //joins all the keys as a single string
    def c = tec2a.flatten().join("+") 
    return c
}

// orders the modificators keys ('Ctrl' goes before than 'Alt' goes before than 'Shift')
def reordena(ts){
    def orden = ['Ctrl','Alt','Shift']
    def mc = { a, b -> orden.indexOf(a) <=> orden.indexOf(b)}
    ts.sort(mc)
}

//uses translationMap loaded from file to "translate" some of the texts
def traduce(t){
    //elvis operator:
        //if there is translation -> use it
        //      else -> use the original string
    return translationMap[t]?:t
}

// reads file (line by line) and loads its information in a map
// ignores comment lines (starting with #)
// and uses only the one with an "="
def getMapFromFile(String path){
    def file = new File(path)
    return getMapFromFile(file)
}

def getMapFromFile(File file){
    def myMap = [:]
    if (file?.exists()){
        file.eachLine{line-> 
            if (line.contains('=') && (!line.startsWith("#"))) {        
                myMap[line.split('=')[0]] = line.split('=')[1] 
            } 
        } 
    } else { c.statusInfo = "translation file not found"}
    return myMap
}

def formatName(s){
    def elipse = ((char) 8230).toString()
    return s.replace(elipse,"...")
}