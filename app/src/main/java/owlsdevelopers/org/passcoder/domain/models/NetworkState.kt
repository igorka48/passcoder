package owlsdevelopers.org.passcoder.domain.models

class NetworkState(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Failed")
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}