package com.example.registrationform

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.registrationform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //Hide keyboard on Enter key
        binding.PersonName.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.editTextTextEmailAddress.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.editTextDate.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.editTextTextConfirmPassword2.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.editTextTextPassword.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }






        //Action Listener for the ok Button
        binding.registerButton.setOnClickListener {

         //on every click make sure the hint set as default
            binding.PersonName.hint = "Full Name"
            binding.editTextTextEmailAddress.hint = "Email Address"
            binding.editTextDate.hint = "Birthday Date"
            binding.editTextTextPassword.hint = "Password"
            binding.editTextTextConfirmPassword2.hint = "Confirm Password"

            binding.textViewInfo.text = ""





            // if the user enter all the info , the password is the same print the info
            //and check the email pattern
            //or show warning message
            if (isTextEmpty() && passwordCheck() && emailPattern(binding.editTextTextEmailAddress.editText?.text.toString())) {
                binding.textViewInfo.text = " \n Full Name: ${binding.PersonName.editText?.text.toString()} \n Email: ${binding.editTextTextEmailAddress.editText?.text.toString()} \nBirthDay: ${binding.editTextDate.editText?.text.toString()} \nGender: ${genderCheck()}"
            } else if (!passwordCheck()) {
                binding.editTextTextConfirmPassword2.hint = "Passwords should match!"
                binding.editTextTextPassword.hint = "Passwords should match!"


            }else   if(!emailPattern(binding.editTextTextEmailAddress.editText?.text.toString())){
                binding.editTextTextEmailAddress.hint = "Enter valid email!!"
            }

        }





}

//fun to check the email pattern
fun emailPattern(email: String): Boolean{
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[com]+"
  return email.matches(emailPattern.toRegex())
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

    return binding.editTextTextPassword.editText?.text.toString().equals(binding.editTextTextConfirmPassword2.editText?.text.toString())
}

//fun to check if the user enter the info or not
fun isTextEmpty(): Boolean {

    val nameCheck = isNameEmpty(binding.PersonName.editText?.text.toString().isNotEmpty())
    val emailCheck = isEmailEmpty(binding.editTextTextEmailAddress.editText?.text.toString().isNotEmpty())
    val cheacBD = isBDEmpty(binding.editTextDate.editText?.text.toString().isNotEmpty())
    val pass1Check = ispassEmpty(binding.editTextTextPassword.editText?.text.toString().isNotEmpty())
    val pass2Check = ispassEmpty(binding.editTextTextConfirmPassword2.editText?.text.toString().isNotEmpty())

    return nameCheck && emailCheck && cheacBD && pass1Check && pass2Check
}



   // fun to check if the user enter the BD or not
    fun isBDEmpty(bd:Boolean): Boolean {
        return if (!bd){
            binding.editTextDate.hint = "Enter Your BD!"
            false
        }else true
    }

    //fun to check if the user enter the email or not
    fun isEmailEmpty(email :Boolean): Boolean {
        return if (!email){
            binding.editTextTextEmailAddress.hint = "Enter Your Email!"
            false
        }else true
    }

    //fun to check if the user enter the name or not
    fun isNameEmpty(name : Boolean): Boolean {
        return if (!name){
            binding.PersonName.hint = "Enter Your name!"
            false
        }else true
    }

    // fun to check if the user enter the Password or not
    fun ispassEmpty(pass : Boolean): Boolean {
        return if (!pass){
            binding.editTextTextPassword.hint = "Enter Your Password!"
            false
        }else true
    }

    // fun to check if the user enter the Password or not
    fun ispass2Empty(pass : Boolean): Boolean {
        return if (!pass){
            binding.editTextTextConfirmPassword2.hint = "Enter Your Password!"
            false
        }else true
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }


}