package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res

enum class CertStatusCode(val value: String) {
    REQUEST_COMPLETE(value = "Запрос сертификата одобрен"),
    INVALID_LANGUAGE(value = "В запросе указан неверный язык"),
    INVALID_BIN(value = "Запрос сертификата отклонен из-за некорректного BIN"),
    SIG_VALIDATION_FAIL(value = "Запрос сертификата отклонен по причине некорректной подписи"),
    DECRYPTION_ERROR(value = "Запрос сертификата отклонен из-за ошибки дешифрования"),
    REQUEST_IN_PROGRESS(value = "Запрос сертификата обрабатывается"),
    REJECTED_BY_ISSUER(value = "Запрос сертификата отклонен эмитентом карты"),
    REQUEST_PENDED(value = "Запрос сертификата ждет обработки СА"),
    REJECTED_BY_AQUIRER(value = "Запрос сертификата отклонен банком продавца (получателем)"),
    REG_FORM_ANSWER_MALFORMED(value = "Запрос сертификата отклонен из-за неверной позиции в регистрационной форме"),
    REJECTED_BY_CA(value = "Запрос сертификата отклонен центром сертификации"),
    UNABLE_TO_ENCRYPT_CERT_RES(value = "Центр сертификации не получил ключа и по этой причине не может зашифровать отклик для владельца карты");
}
