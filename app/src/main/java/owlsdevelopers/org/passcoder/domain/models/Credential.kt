package owlsdevelopers.org.passcoder.domain.models

data class Credential(val login: String, val password: String, val token: String) {
    constructor(login: String, password: String) : this(login, password, "")
    constructor(token: String) : this("", "", token)
}