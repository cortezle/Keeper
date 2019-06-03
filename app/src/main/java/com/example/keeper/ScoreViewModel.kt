package com.example.keeper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel(){
    private val _scoreTeamA= MutableLiveData<String>()
    val scoreTeamA : LiveData<String>
        get() = _scoreTeamA

    private val _scoreTeamB= MutableLiveData<String>()
    val scoreTeamB :LiveData<String>
        get() = _scoreTeamB



    init {
        _scoreTeamA.value = "0"
        _scoreTeamB.value = "0"
    }


    fun addOneTeamA() {
        _scoreTeamA.value = _scoreTeamA.value?.toInt()?.plus(1).toString()


        Log.d("hey:", scoreTeamA.value.toString())
    }

    fun addOneTeamB() {
        _scoreTeamB.value = _scoreTeamB.value?.toInt()?.plus(1).toString()

    }

    fun addTwoTeamA() {
        _scoreTeamA.value = _scoreTeamA.value?.toInt()?.plus(2).toString()
    }

    fun addTwoTeamB() {
        _scoreTeamB.value = _scoreTeamB.value?.toInt()?.plus(2).toString()
    }

    fun addThreeTeamA() {
        _scoreTeamA.value = _scoreTeamA.value?.toInt()?.plus(3).toString()
    }

    fun addThreeTeamB() {
        _scoreTeamB.value = _scoreTeamB.value?.toInt()?.plus(3).toString()
    }

    fun resetScores() {
        _scoreTeamA.value = "0"
        _scoreTeamB.value = "0"



    }
}