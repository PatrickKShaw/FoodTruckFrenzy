package foodtruckfrenzy.Helper;

/**
 * Enum which contains the shorthand codes for all the board elements which can be created
 * These elements are to be read from the layout.csv file and translated using the board element factory
 * The mapping is as follows:
 * 
    O: Obstruction
    S: Speed Trap
    Q: Recipe

    P: Pot Hole
    L: Pot Hole Vertical

    F: Food
    R: Food Vertical

    H: Horizontal Road
    V: Vertical Road
    X: 4 way intersect

    U: North-West corner
    I: North-East corner
    J: South-East corner
    K: South-West corner

    N: North three-way
    B: East three-way
    M: South three-way
    C: West three-way 
 */
enum LayoutEnum {
    H,
    V,
    X,

    U,
    I,
    J,
    K,
    
    N,
    B,
    M,
    C,

    O,
    S,
    P,
    F,
    R,
    L,
    Q
}
