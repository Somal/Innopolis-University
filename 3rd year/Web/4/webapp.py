# -*- coding: utf-8 -*- 
from bottle import Bottle, run
from bottle import static_file, request, response, get, post, template, redirect
import bottle

app = Bottle()

STATIC_ROOT = ""

@app.get('/')
def red():
    redirect("/hello")

@app.get('/hello')
def hello():
    visited=request.get_cookie("visited")
    print(visited)
    if (visited == "yes"):

        return static_file('index_registered.html', root=STATIC_ROOT)
        print("yes")
    else:
        print("index.html")
        return bottle.static_file('index.html',STATIC_ROOT)
        #return static_file('index.html', root=STATIC_ROOT)


@app.get('/login')
def login_page():
    if (request.get_cookie("visited")=="yes"):
        redirect("/")
    return static_file('login.html', root=STATIC_ROOT)

@app.post('/login')
def login():
    username = request.forms.get('username')
    password = request.forms.get('password')
    if username == 'admin' and password == '123':
        response.set_cookie("visited", "yes")
        redirect('/')
    else:
        redirect("/login")

@app.get('/logout')
def logout():
    #response.delete_cookie("visited")
    response.set_cookie("visited", "12")
    print("logount")
    redirect('/')
run(app, host='localhost', port=8090)
