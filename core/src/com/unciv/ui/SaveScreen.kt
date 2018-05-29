package com.unciv.ui

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.unciv.UnCivGame
import com.unciv.logic.GameSaver
import com.unciv.ui.cityscreen.addClickListener
import com.unciv.ui.pickerscreens.PickerScreen
import com.unciv.ui.utils.enable
import com.unciv.ui.utils.getRandom

class SaveScreen : PickerScreen() {
    val textField = TextField("", skin)

    init {
        val currentSaves = Table()

        currentSaves.add(Label("Current saves:",skin)).row()
        val saves = GameSaver().getSaves()
        saves.forEach {
            val textButton = TextButton(it, skin)
            textButton.addClickListener {
                textField.text = it
            }
            currentSaves.add(textButton).pad(5f).row()

        }
        topTable.add(currentSaves)


        val newSave = Table()
        val adjectives = listOf("Prancing","Obese","Junior","Senior","Abstract","Discombobulating","Simple","Awkward","Holy",
                "Dangerous","Greasy","Stinky","Purple","Majestic","Incomprehensible","Cardboard","Chocolate","Robot","Ninja")
        val nouns = listOf("Moose","Pigeon","Weasel","Ferret","Onion","Marshmellow","Crocodile","Inu Shiba",
                "Sandwich","Elephant","Kangaroo","Marmot","Beagle","Dolphin","Fish","Tomato","Duck")
        val defaultSaveName = adjectives.getRandom()+" "+nouns.getRandom()
        textField.text = defaultSaveName

        newSave.add(Label("Saved game name:",skin)).row()
        newSave.add(textField).width(300f)

        topTable.add(newSave)
        topTable.pack()

        rightSideButton.setText("Save game")
        rightSideButton.addClickListener {
            GameSaver().saveGame(UnCivGame.Current.gameInfo, textField.text)
            UnCivGame.Current.setWorldScreen()
        }
        rightSideButton.enable()
    }

}