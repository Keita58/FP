using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SanchezLopezMarc_ExamenModelA
{
    public class Context : DbContext
    {
        public Context() : base("Examen") { }

        public DbSet<Alumne> Alumnes { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            Database.SetInitializer(new DropCreateDatabaseAlways<Context>());
            base.OnModelCreating(modelBuilder);
            modelBuilder.HasDefaultSchema("Examen");

            modelBuilder.Entity<Alumne>()
                .HasMany<Alumne>(s => s.IDMentor)
                .WithMany(c => c.IDMentorat)
                .Map(cs =>
                {
                    cs.MapLeftKey("IDMentor");
                    cs.MapRightKey("IDMentorat");
                    cs.ToTable("Mentoria");
                });
        }
    }
}
