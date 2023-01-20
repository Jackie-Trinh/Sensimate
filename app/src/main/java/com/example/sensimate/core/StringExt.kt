package com.example.sensimate.core

import android.util.Patterns
import java.util.regex.Pattern

// Constants for password requirements
private const val MIN_PASS_LENGTH = 8
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

// Checks if the sex is valid by checking if the input is not blank
// TODO Add a check for 'mand' or 'kvinde', note: not gonna kick that hornets nest
fun String.isValidSex(): Boolean {
    return this.isNotBlank()
}

// Checks if the age is valid by checking if the input is not blank
// TODO Add a check for format matching a date
fun String.isValidAge(): Boolean {
    return this.isNotBlank()
}

// Checks if Postal code is not blank
// TODO Add a check for postal code format
fun String.isValidPostal(): Boolean {
    return this.isNotBlank()
}

// Checks if the email is not blank and matches email pattern
fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

// Checks if the password is valid by checking it is not blank and that it matches the requirements
fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASS_LENGTH &&
            Pattern.compile(PASS_PATTERN).matcher(this).matches()
}

// Checks if the password matches, used for repeating the password on signup
fun String.passwordMatches(repeated: String): Boolean {
    return this == repeated
}

fun String.idFromParameter(): String {
    return this.substring(1, this.length - 1)
}
