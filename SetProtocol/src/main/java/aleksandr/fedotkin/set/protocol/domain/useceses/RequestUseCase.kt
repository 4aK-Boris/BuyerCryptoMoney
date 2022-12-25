package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.domain.models.Model

abstract class RequestUseCase<T : Model, R : DTO> : ProcessingErrorUseCase<T, R>()