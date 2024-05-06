const express = require('express')

const bookModel = require('./db.js')

const app = express()

const bodyParser = require('body-parser')

const fileUpload = require('express-fileupload')

const path = require('path')

app.use(fileUpload())


app.use(express.static(__dirname+'/public'))

app.use(bodyParser.urlencoded({extended:true}))

app.set('view engine','ejs')

app.listen(5500,()=>{
    console.log("App is listening at port 5500!!!")
})

//addBook

app.get('/addbook',(req,res)=>{

    res.render("bookForm.ejs")
})

// bname=&aname=&price=&pub=&gen=

app.post('/addbook',(req,res)=>{

    const form_data = req.body

    let image = req.files.image
//image=img1.jpg&bname=Lost+world&aname=J+K+sSmons&price=3000&pub=Ancient+Research+Archives&gen=Fiction
    image.mv(path.resolve(__dirname,'public/images',image.name),async(error)=>{
        await 
        bookModel.create({
                name :form_data.bname ,
                author : form_data.aname,
                price : form_data.price ,
                publisher : form_data.pub,
                genre : form_data.gen,
                image : '/images/'+image.name
            },(error,book_added)=>{

        
        if(error){
            console.log(error)
        }
        else{
            console.log(book_added)
           res.render("display.ejs",{data:book_added})

        
            
        }
        

    
    })

})})

app.get('/allbooks',(req,res)=>{


    bookModel.find({},(error,all_books)=>{

        if(error){
            console.log(error)
        }
        else{

              //  res.send(all_books)

              res.render('all.ejs',{data:all_books})
        }

    })
    
})

// http://localhost:4300/insertbook?bk=&ar=&pbr=&pri=&gnr=