
texto = new StringBuilder()
texto << "group:          freeplane\nprocess:        javaw\n\n# Reference: Freeplane_KeyMap_for_Carnac\n\nshortcuts:\n"

def menu = "main_menu"
def keyStrokes = menuUtils.createAcceleratebleMenuEntryTree(menu)

def scriptDir2 = new File(this.class.protectionDomain.codeSource.location.path).parent
mapa = getMapFromFile("${scriptDir2}/keysDict.txt")

caso (keyStrokes)

def ymlFile = new File("${scriptDir2}/freeplane.yml")
ymlFile.text = texto

node.createChild("result").note = texto
c.statusInfo = "freeplane.yml updated with actual keymap"


// ----methods---------------
def caso(n){
    if(n.leaf){
        def menuEntry = n.getUserObject()
        texto <<  "    - name: ${menuEntry.toString()}"  <<"\n"
        << "      keys:"  <<"\n"
        <<  "      - ${formatCarnac(menuEntry.getKeyStroke())}"  <<"\n"
     }
    n.children.each{
        caso(it )
    }
}

def formatCarnac(k){
    def teclas = k.toString().replace("pressed ","").toLowerCase().split(" ")*.capitalize()
    def tec2a =  [] << (teclas.size()>=2?teclas.size() == 2?teclas[0]:reordena(teclas[0 .. -2]):[]) << traduce(teclas[-1])

    def c = tec2a.flatten().join("+")
    return c
}

def reordena(ts){
    def orden = ['Ctrl','Alt','Shift']
    def mc = { a, b -> orden.indexOf(a) <=> orden.indexOf(b)}
    ts.sort(mc)
}

def traduce(t){
    return mapa[t]?:t
}

def getMapFromFile(path){
    def file = new File(path)
    def map = [:]
    file.eachLine{line-> 
        if (line.contains('=') && (!line.startsWith("#"))) {        
            map[line.split('=')[0]] = line.split('=')[1] 
        } 
    } 
    return map
}
