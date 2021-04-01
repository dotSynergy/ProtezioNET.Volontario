package it.insubria.protezionet.Common

/**
 * Definisce le persone user/admin
 */
data class Person(val nome: String, val password: String, val ruolo: String, val isAdmin: Boolean) {

}