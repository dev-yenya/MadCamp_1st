package com.example.first_app

import android.content.res.AssetManager
import android.os.ParcelFileDescriptor.open
import androidx.lifecycle.ViewModel

class PhoneBookViewModel: ViewModel() {

    var phoneNumberList = listOf(
        PhoneNumber("엄마", "010-0000-0000"),
        PhoneNumber("아빠", "010-1111-1111"),
        PhoneNumber("형", "010-2222-2222"),
        PhoneNumber("누나", "010-3333-3333"),
        PhoneNumber("남동생", "010-4444-4444"),
        PhoneNumber("여동생", "010-5555-5555"),
        PhoneNumber("할아버지", "010-6666-6666"),
        PhoneNumber("할머니", "010-7777-7777"),
        PhoneNumber("외할아버지", "010-8888-8888"),
        PhoneNumber("외할머니", "010-9999-9999"),
        PhoneNumber("증조할아버지", "010-1212-3434"),
        PhoneNumber("고조할아버지", "010-1234-5678"))
}

class PhoneNumber(name:String, phoneNumber: String){
    private val name: String = name
    private val phoneNumber: String = phoneNumber

    fun getName(): String {
        return name
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }
}