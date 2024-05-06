const mongoose = require('mongoose')

mongoose.connect('mongodb+srv://test123:Conestoga@cestar-node.wzsxe.mongodb.net/ConestogaBooks?retryWrites=true&w=majority',
{useNewUrlParser: true, useUnifiedTopology: true},(error)=>{
    if(error){
        console.log(error)
    }
    else{
        console.log("Connected!!!")
    }
}
)

const bookSchema = mongoose.Schema({
    name : String ,
    author : String ,
    price : Number,
    publisher :String ,
    genre : String,
    image : String
})

const bookModel = mongoose.model('book',bookSchema)

// const addBook = bookModel.create({
//     name : "Science and Reaility",
//     author : "JJ Thomson",
//     price : 500,
//     publisher : "Greek Mart",
//     genre : "Science Fication",
    
// },(error,book_added)=>{
//     if(error){
//         console.log(error)
//     }
//     else{
//         console.log(book_added)
//     }
// })

 

module.exports = bookModel

