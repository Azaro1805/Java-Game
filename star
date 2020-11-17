import csv
import numpy as np
import pandas as pan

'''def readCsv (fileName):
    with open(fileName, 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        return csv_reader'''

def createDB (data):
    CityDB = np.array(data)
    CityDB2 = [ [ "" for j in range(3) ] for i in range(len(CityDB)) ]

    for i in range(len(CityDB)):
        for j in range(len(CityDB[i])):
            if(CityDB[i][0] != CityDB[i][1]):
                CityDB2[i][j] = CityDB[i][j]

    return CityDB2

def check_In_DB (CityName, CityDB):

    print(CityDB.any(CityName))
    '''for i in range (len(CityDB)):
        for j in range(len(CityDB[0])):
            if (CityDB[i][j] == CityName):
                return True
    return False'''

def find_path(starting_locations, goal_locations, search_method, detail_output):
    if (detail_output):
       pathFound = False
       path = starting_locations + " -> "


       if(pathFound):
           print(path)
       else:
           print("No path found.")
    else:
        print("If the binary detail_output variable is true,Print out the heuristic value of the the state of your "
              "locations after the First transformation.")

def neighborsRingArray():
    neibSet = set()

    for i in range(len(CityDB)):
        if i not in neibSet:
            neibSet.add(CityDB[i][0])

    neibArray = [ [ "" for j in range(2) ] for i in range(len(neibSet)-1) ]

    j=-1
    for i in (neibSet):
        neibArray[j][0] = i
        j = j+1

    neibSet.clear()
    return neibArray

def addRingNum(locations , RingNumber):
    for i in range(len(neighborsRing)):
        if(neighborsRing[i][0] == locations and neighborsRing[i][1] == ""):
            neighborsRing[i][1] = RingNumber
            print("the location :", neighborsRing[i][0] , " , the number of ring after add :", neighborsRing[i][1])

def myNeighborsSet(locations):
    NeibOfLocations = set()
    for i in range(len(CityDB)):
            #print("i = " , i , " , ", CityDB[i][1],"==",locations)
            if(CityDB[i][0]==locations and CityDB[i][1]!="" ):
                #print("add" , CityDB[i][1])
                NeibOfLocations.add(CityDB[i][1])
    return NeibOfLocations

def makeRing(locations , RingNumber):
    NeibOfLocations = myNeighborsSet(locations)
    print("neibset of  = ", locations, " , is: " , NeibOfLocations)
    for i in NeibOfLocations:
        addRingNum(i , RingNumber)

def makeEndLocationRing(endLocation):
    addRingNum(endLocation, "0")
    makeRing(endLocation, "1")

def add1tostr(string):
    string = int(string) + 1
    string = str(string)
    return string

def recorRun (startLocation, endLocation ):
    flag = True
    makeEndLocationRing(endLocation)
    RingNumber = "0"

    print("neighborsRing = " , np.matrix(neighborsRing) )
    print("###################################### Start while hyoristic : #######################################")
    print()
    while (flag):
        RingNumber = add1tostr(RingNumber)
        print("flag = ", flag , ", RingNumber = " , RingNumber)
        for i in range(len(neighborsRing)):
            if (flag == False):
                break
            #print("neighborsRing[i][1] =" , neighborsRing[i][1] , "RingNumber = " , RingNumber)
            if(neighborsRing[i][1] == RingNumber):
                #print("neibSet befor = ", myNeighborsSet(neighborsRing[i][0]))
                #neibSet = myNeighborsSet(neighborsRing[i][0])
                RingNumber2 = add1tostr(RingNumber)
                print("neighborsRing = ", np.matrix(neighborsRing))
                print("###################################### new firends of the ring  : #######################################")
                print("name  = ", neighborsRing[i][0] , ", his RingNumber = ", neighborsRing[i][1] ,", new ring =",RingNumber2)
                print()
                makeRing(neighborsRing[i][0], RingNumber2)
                if (neighborsRing[i][0] == startLocation):
                    print("%%%%%%%%%%%%%%%%%%%%%%%%%% END %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                    print("i = startLocation =", neighborsRing[i][0])
                    flag = False
                    break
    print("neighborsRing = ", np.matrix(neighborsRing))


def hiyo(startLocation, endLocation):
    '''צריך להכניס לסוף טבעת 0 '''
    recorRun(startLocation, endLocation)

def test (startLocation, endLocation ):
    makeRing(startLocation, "3")

'''בקובץ יש את כל עיר והעיר הבאה/ הקרובה ביותר היא אותה עיר (אפשר להוריד עם לולאה או פשוט לתת לו ערך מוזר שלא יגמור לו לחזור לאותה עיר )'''

print("Start the Algorithm")

data = pan.read_csv(r'C:\Users\oraza\Downloads\db1.csv')
CityDB = createDB(data)
'''CityDB[neighborname][countyname]'''
#print(CityDB[0][0])
#print(np.matrix(CityDB))


neighborsRing = neighborsRingArray()

print("neighborsRing = ", np.matrix(neighborsRing))

'''for i in range(len(neighborsRing)):
    print(i)
    print("name  = " , neighborsRing[i][0])
    print("set =  ", myNeighborsSet(neighborsRing[i][0]))'''

#test("Autauga County, AL" , "Shelby County, AL")
start = "Autauga County, AL"
end = "Russell County, KY"
hiyo(start , end)

print("********************* finish *****************")
for i in neighborsRing:
    if (i[0]=="Autauga County, AL"):
        print(" ring ", start," County is = " ,i[1])
    if (i[0]==end):
        print(" ring ", end," County is = " ,i[1])

'''neighbors'''


'''check if location is in DB'''
'''city = "Washington County, UT"
print()


print("first " , CityDB[0] )
'''
'''print("The city ", city, "is in the DB ? ", check_In_DB(city, CityDB))
'''
'''find path for the voters'''
'''print()
find_path("Washington County, UT", "San Diego County, CA", "C", False)'''


