package id.dupat.chatkuy.viewmodel.state

sealed class ViewState {
    data class OnRun(var what : Int? = null ) : ViewState()
    data class ShowToast(var message : String) : ViewState()
    data class IsLoading(var state : Boolean = false) : ViewState()
    data class Error(var err : String? = null,var viewErr: String? = null) : ViewState()
    data class IsSuccess(var what : Int? = null) : ViewState()
    data class SuccessMessage(var msg: String? = null): ViewState()
    object Reset : ViewState()
}