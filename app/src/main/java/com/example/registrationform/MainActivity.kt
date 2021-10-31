package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrationform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Action Listener for the ok Button
        binding.registerButton.setOnClickListener {

            // if the user enter all the info and the password the same print the info
            //or print warning message
            if (isTextEmpty() && passwordCheck()) {
                binding.textViewInfo.text =
                    " \n Full Name: ${binding.PersonName.text.toString()} \n Email: ${binding.editTextTextEmailAddress.text.toString()} \nBirthDay: ${binding.editTextDate.text.toString()} \nGender: ${genderCheck()}"
            } else if (!passwordCheck()) {
                binding.textViewInfo.text = "Passwords should match!"
            } else {
                binding.textViewInfo.text = "Enter a valid information"
            }

        }
    }

    //fun to return the chosen gender
    fun genderCheck(): String {
        return when(binding.gender.checkedRadioButtonId){
            R.id.female -> "Female"
            else -> "Male"
        }
    }

    //fun to check if the passwords match or not
    fun passwordCheck(): Boolean {
        val textInPassword = binding.editTextTextPassword.text.toString()
        val textInConfirm = binding.editTextTextConfirmPassword2.text.toString()
        return textInPassword == textInConfirm
    }

    //fun to check if the user enter the info or not
    fun isTextEmpty(): Boolean {
        val textInName = binding.PersonName.text
        val nameCheck = isNameEmpty(textInName.toString())

        val textInEmail = binding.editTextTextEmailAddress.text
        val emailCheck =  isEmailEmpty(textInEmail.toString())


        val textInBD = binding.editTextDate.text
        val cheacBD = isBDEmpty(textInBD.toString())

        return nameCheck && emailCheck && cheacBD
    }

    //fun to check if the user enter the BD or not
    fun isBDEmpty(dt: String): Boolean {
        return if (dt == "Birthday Date" || dt == "" || dt == "Enter Your BD!!"){
            binding.editTextDate.setText("Enter Your BD!!")
            false
        }else true
    }

    //fun to check if the user enter the email or not
    fun isEmailEmpty(email: String): Boolean {
        return if (email == "Email Address" || email=="" || email == "Enter Your Email!!" ){
            binding.editTextTextEmailAddress.setText("Enter Your Email!!")
            false
        }else true
    }

    //fun to check if the user enter the name or not
    fun isNameEmpty(name: String): Boolean {
        return if (name == "Full Name" || name =="" || name == "Enter Your name!!"){
            binding.PersonName.setText("Enter Your name!!")
            false
        }else true
    }
}