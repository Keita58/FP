from django.contrib.auth.mixins import LoginRequiredMixin
from django.shortcuts import render
from django.views import View

from examen.models import Presoners, PresonerPolicia


# Create your views here.

class LlistarPresonerPolicia(LoginRequiredMixin, View):
    def get(self, request):
        if request.user.is_authenticated:
            context = {
                "presonerpolicia":
                    list(PresonerPolicia.objects.all())
            }
            return render(request, 'LlistaPresonersPolicia.html', context)
        else:
            return render(request, 'login.html')


class LlistarPresoners(LoginRequiredMixin, View):
    def get(self, request):
        context = {
            "presoners":
                list(Presoners.objects.all())
        }
        return render(request, 'LlistaPresoners.html', context)


class AfegirPresoners(LoginRequiredMixin, View):
    def get(self, request):
        pass


class EditarPresoners(LoginRequiredMixin, View):
    def get(self, request):
        pass


class EliminarPresoners(LoginRequiredMixin, View):
    def get(self, request):
        pass