package se.wintren.quickdrop

enum class Photo(val path: String) {
    Wedding("atWedding.JPG"),
    Beach("onBeach.gif"),
    Rx("oneDoesNot.jpg"),
    Sabinth("sabinth.png"),
    Smorre("smorreBrod.JPG"),
    Windy("windyBilo.jpg");

    companion object {
        fun random(): Photo = values().random()
    }
}