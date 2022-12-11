package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface EXHRepository {

    suspend fun <T, P, TD, PD> encrypt(
        r: PublicKey,
        t: T,
        p: P,
        mapT: (T) -> TD,
        mapP: (P) -> PD,
        tSerializer: KSerializer<TD>,
        pSerializer: KSerializer<PD>
    ): CryptoDataModel
}