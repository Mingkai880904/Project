weight=eval(input("enter your weight KG:"))
height=eval(input("enter your height CM:"))

height1=height/100
bmi=weight/(height1*height1)

print("BMI is",format(bmi,".2f"))
if bmi<18.5:
    print("太低")
elif bmi<25:
    print("剛剛好")
elif bmi<30:
    print("太高")
else:
    print("fat")
