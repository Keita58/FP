# Generated by Django 5.1.7 on 2025-04-01 14:12
from datetime import datetime

from django.contrib.auth.models import User, Group
from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('examen', '0001_initial'),
    ]

    def create_usuaris(apps, schema_editor):
        User.objects.create_user(username='hector', password='super3', email='hector@correu.cat')
        User.objects.create_user(username='eric', password='super3', email='eric@correu.cat')
        User.objects.create_user(username='marc', password='super3', email='marc@correu.cat')
        Group.objects.get_or_create(name='Rol1')
        Group.objects.get_or_create(name='Rol2')

    def create_presoners(apps, schema_editor):
        Presoners = apps.get_model('examen', 'Presoners')
        Presoners.objects.create(nom="Pres1", edat=20, motiuDetencio="Si", timestamp=datetime.now())
        Presoners.objects.create(nom="Pres2", edat=130, motiuDetencio="Ah", timestamp=datetime.now())
        Presoners.objects.create(nom="Pres3", edat=47, motiuDetencio="Patata", timestamp=datetime.now())
        Presoners.objects.create(nom="Pres4", edat=13, motiuDetencio=":)", timestamp=datetime.now())
        Presoners.objects.create(nom="Pres5", edat=56, motiuDetencio=":(", timestamp=datetime.now())

    def create_presonerPolicias(apps, schema_editor):
        PresonerPolicia = apps.get_model('examen', 'PresonerPolicia')
        presoners = apps.get_model('examen', 'Presoners')
        users = apps.get_model('examen', User)

        presonersList = presoners.objects.all()
        usersList = users.objects.all()
        presonersList.get(0).policia = usersList.filter(id=1).first()
        presonersList.get(1).policia = usersList.filter(id=2).first()
        presonersList.get(2).policia = usersList.filter(id=2).first()
        presonersList.get(3).policia = usersList.filter(id=3).first()
        presonersList.get(4).policia = usersList.filter(id=3).first()
        PresonerPolicia.objects.create()

    operations = [
        migrations.RunPython(create_usuaris),
        migrations.RunPython(create_presoners),
        migrations.RunPython(create_presonerPolicias),
    ]