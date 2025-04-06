from django.contrib.auth.models import User
from django.db import models
from django.db.models import ManyToManyField


# Create your models here.

class Presoners(models.Model):
    idPresoner = models.AutoField(primary_key=True)
    nom = models.CharField('nom', max_length=1750)
    edat = models.IntegerField('edat')
    motiuDetencio = models.CharField('motiuDetencio', max_length=1750)
    timestamp = models.DateTimeField('timestamp')
    policia = ManyToManyField(User, through="PresonerPolicia")
    def __str__(self):
        return self.nom


class PresonerPolicia(models.Model):
    idPresonerPolicia = models.AutoField(primary_key=True)
    presoners = models.ForeignKey(Presoners, on_delete=models.CASCADE)
    policia = models.ForeignKey(User, on_delete=models.CASCADE)
    def __str__(self):
        return self.idPresonerPolicia
