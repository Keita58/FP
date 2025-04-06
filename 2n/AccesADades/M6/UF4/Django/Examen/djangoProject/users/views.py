from django.contrib.auth import authenticate, login, logout
from django.shortcuts import render, redirect
from django.views import View


# Create your views here.
class LoginView(View):
    def get(self, request):
        if request.user.is_authenticated:
            return redirect('llistarPresonerPolicia')
        else:
            return render(request, 'login.html')

    def post(self, request):
        # Mètode autenticate -> serveix per autenticar.
        # Ell mateix encritpta i desencripta la credencial
        user = authenticate(request, username=request.POST.get('username'), password=request.POST.get('password'))
        # Comprovem que existeix un usuari
        print (user)
        if user is not None:
            # Mètode de django que obre una sessió d'usuari
            login(request, user)
            return redirect('login')
        # Fem aquest return per permetre tornar a fer login
        return self.get(request)

class LogoutView(View):
    def get(self, request):
        if request.user.is_authenticated:
            logout(request)
        return redirect('login')
