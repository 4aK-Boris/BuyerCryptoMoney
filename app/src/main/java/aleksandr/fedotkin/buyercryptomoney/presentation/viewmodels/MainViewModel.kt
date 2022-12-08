package aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels

import aleksandr.fedotkin.buyercryptomoney.core.BaseViewModel

class MainViewModel: BaseViewModel() {
    override fun getErrorActionsMap(): Map<Int, () -> Unit> {
        return emptyMap()
    }
}
