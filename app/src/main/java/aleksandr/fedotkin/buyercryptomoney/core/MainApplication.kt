package aleksandr.fedotkin.buyercryptomoney.core

import aleksandr.fedotkin.buyercryptomoney.core.di.appModule
import android.app.Application
import com.onesignal.OneSignal
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
    }

    companion object {
        private const val ONESIGNAL_APP_ID = "df506608-20fc-460e-8e77-d296037b1a7a"
    }

}
