package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.SnippetDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.SnippetModel

class SnippetMapper {

    fun map(snippetDTO: SnippetDTO): SnippetModel {
        return SnippetModel(
            id = snippetDTO.id,
            sellerId = snippetDTO.sellerId,
            quantity = snippetDTO.quantity,
            imageUrl = snippetDTO.imageUrl,
            title = snippetDTO.title,
            rating = snippetDTO.rating,
            price = snippetDTO.price
        )
    }
}