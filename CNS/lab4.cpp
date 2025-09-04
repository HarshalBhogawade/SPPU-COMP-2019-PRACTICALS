#include <bits/stdc++.h>
using namespace std;

// Function to calculate parity bit
int parity(int arr[], int n, int pos) {
    int p = 0;
    for (int i = 1; i <= n; i++) {
        if (i & pos) {
            p ^= arr[i];
        }
    }
    return p;
}

// Function to encode 7/8 bit ASCII data using Hamming(12,8)
vector<int> encodeHamming(vector<int> data) {
    int n = 12; // total bits (data + parity)
    vector<int> code(n + 1, 0); // 1-based indexing

    // Place data bits at non-power-of-2 positions
    int j = 0;
    for (int i = 1; i <= n; i++) {
        if (i == 1 || i == 2 || i == 4 || i == 8) continue; // parity positions
        code[i] = data[j++];
    }

    // Calculate parity bits
    code[1] = parity(&code[0], n, 1);  // p1
    code[2] = parity(&code[0], n, 2);  // p2
    code[4] = parity(&code[0], n, 4);  // p4
    code[8] = parity(&code[0], n, 8);  // p8

    return code;
}

// Function to detect and correct error
void detectAndCorrect(vector<int>& code) {
    int n = code.size() - 1;
    int p1 = parity(&code[0], n, 1);
    int p2 = parity(&code[0], n, 2);
    int p4 = parity(&code[0], n, 4);
    int p8 = parity(&code[0], n, 8);

    int errorPos = (p8 << 3) | (p4 << 2) | (p2 << 1) | p1;

    if (errorPos == 0) {
        cout << "No error detected ✅" << endl;
    } else {
        cout << "Error detected at bit position: " << errorPos << endl;
        code[errorPos] ^= 1; // flip the erroneous bit
        cout << "Error corrected ✅" << endl;
    }
}

// Function to extract original data
vector<int> decodeHamming(vector<int> code) {
    vector<int> data;
    for (int i = 1; i < code.size(); i++) {
        if (i == 1 || i == 2 || i == 4 || i == 8) continue; // skip parity bits
        data.push_back(code[i]);
    }
    return data;
}

int main() {
    string text;
    cout << "Enter a character: ";
    cin >> text;

    // Take ASCII of character (8-bit)
    int ascii = int(text[0]);
    vector<int> dataBits;

    for (int i = 7; i >= 0; i--) {
        dataBits.push_back((ascii >> i) & 1);
    }

    cout << "Original ASCII (8-bit): ";
    for (auto b : dataBits) cout << b;
    cout << endl;

    // Encode using Hamming Code
    vector<int> code = encodeHamming(dataBits);

    cout << "Encoded Hamming Code (12 bits): ";
    for (int i = 1; i < code.size(); i++) cout << code[i];
    cout << endl;

    // Introduce an error manually (flip a bit)
    code[5] ^= 1; // flip 5th bit to simulate error
    cout << "Received Code (with error at bit 5): ";
    for (int i = 1; i < code.size(); i++) cout << code[i];
    cout << endl;

    // Detect and correct error
    detectAndCorrect(code);

    cout << "Corrected Code: ";
    for (int i = 1; i < code.size(); i++) cout << code[i];
    cout << endl;

    // Decode back to original data
    vector<int> decoded = decodeHamming(code);

    cout << "Decoded ASCII (8-bit): ";
    for (auto b : decoded) cout << b;
    cout << endl;

    int correctedAscii = 0;
    for (int i = 0; i < decoded.size(); i++) {
        correctedAscii = (correctedAscii << 1) | decoded[i];
    }
    cout << "Corrected Character: " << char(correctedAscii) << endl;

    return 0;
}
