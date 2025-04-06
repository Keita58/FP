from django.contrib import admin

from examen.models import Presoners, PresonerPolicia


# Register your models here.

class PresonersAdmin(admin.ModelAdmin):
    pass

class PresonerPoliciaAdmin(admin.ModelAdmin):
    pass

admin.site.register(Presoners, PresonersAdmin)
admin.site.register(PresonerPolicia, PresonerPoliciaAdmin)