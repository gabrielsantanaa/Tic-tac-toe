package com.gabriel.personal.projects.jogodavelha.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.personal.projects.jogodavelha.ui.game.Player.*

class GameViewModel : ViewModel() {

    private val _gameFinishEvent = MutableLiveData<Boolean>()
    val gameFinishEvent: LiveData<Boolean> = _gameFinishEvent

    private val _currentPlayer = MutableLiveData(First)
    val currentPlayer: LiveData<Player> = _currentPlayer

    private val plays = arrayOfNulls<Player>(9)
    private var playsCount = 0

    fun play(position: Int) {
        playsCount++
        plays[position - 1] = currentPlayer.value

        checkWinner()
    }

    private fun checkWinner() {
        //finish the game
        if (
            /*
            if the array item is null, it returns false and goes to the next statement.
             */

            // check rows
            plays[0] ?: false == plays[1] && plays[1] ?: false == plays[2]
            ||
            plays[3] ?: false == plays[4] && plays[4] ?: false == plays[5]
            ||
            plays[6] ?: false == plays[7] && plays[7] ?: false == plays[8]
            ||

            // check columns
            plays[0] ?: false == plays[3] && plays[3] ?: false == plays[6]
            ||
            plays[1] ?: false == plays[4] && plays[4] ?: false == plays[7]
            ||
            plays[2] ?: false == plays[5] && plays[5] ?: false == plays[8]
            ||

            // check horizontally
            plays[0] ?: false == plays[4] && plays[4] ?: false == plays[8]
            ||
            plays[2] ?: false == plays[4] && plays[4] ?: false == plays[6]

        ) {
            resetGame()

        }
        else if (playsCount >= 9) {
            resetGame()
        }
        //continue the game
        else {
            _currentPlayer.value = _currentPlayer.value?.next()
        }
    }

    private fun resetGame() {
        playsCount = 0
        plays.map { null }
        _gameFinishEvent.value = true
    }

}

enum class Player {
    First,
    Second;

    fun next() = when (this) {
        First -> Second
        Second -> First
    }
}