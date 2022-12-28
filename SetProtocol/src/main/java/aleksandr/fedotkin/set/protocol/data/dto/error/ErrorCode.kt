package aleksandr.fedotkin.set.protocol.data.dto.error

import kotlinx.serialization.Serializable

@Serializable
enum class ErrorCode {
    ChallengeMismatch,
    UnknownXID,
    ThumbsMismatch,
    MessageTooNew,
    MessageTooOld,
    SignatureRequired,
    MessageTooBig,
    UnrecognizedExtension,
    VersionTooNew,
    VersionTooOld,
    WrapperMsgMismatch,
    BadMessageHeader,
    SignatureFailure,
    MissingCertificate,
    RevokedCertificate,
    ExpiredCertificate,
    InvalideCertificate,
    DecodingFailure,
    MessageNotSupported,
    UnspecifiedFailure;
}