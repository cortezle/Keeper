package com.example.keeper.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    var nombre: String,
    var equipoA: String,
    var puntosA: Int,
    var equipoB: String,
    var puntosB: Int,
    var fecha: String,
    var tiempo: String,
    @PrimaryKey var idGame:String

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nombre)
        dest.writeString(equipoA)
        dest.writeInt(puntosA)
        dest.writeString(equipoB)
        dest.writeInt(puntosB)
        dest.writeString(fecha)
        dest.writeString(tiempo)
        dest.writeString(idGame)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}